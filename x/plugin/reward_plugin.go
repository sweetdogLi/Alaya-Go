package plugin

import (
	"math/big"
	"sync"

	"github.com/PlatONnetwork/PlatON-Go/x/staking"

	"github.com/PlatONnetwork/PlatON-Go/common"
	"github.com/PlatONnetwork/PlatON-Go/common/vm"
	"github.com/PlatONnetwork/PlatON-Go/core/types"
	"github.com/PlatONnetwork/PlatON-Go/log"
	"github.com/PlatONnetwork/PlatON-Go/x/reward"
	"github.com/PlatONnetwork/PlatON-Go/x/xcom"
	"github.com/PlatONnetwork/PlatON-Go/x/xutil"
)

const RewardNewBlockRate = 2 //

type rewardMgrPlugin struct {
}

var (
	rewardOnce sync.Once
	rm         *rewardMgrPlugin = nil
)

func RewardMgrInstance() *rewardMgrPlugin {
	rewardOnce.Do(func() {
		log.Info("Init Reward plugin ...")
		rm = &rewardMgrPlugin{}
	})
	return rm
}

/*func ClearRewardPlugin() error {
	if nil == rm {
		return common.NewSysError("the RewardPlugin already be nil")
	}
	rm = nil
	return nil
}*/

// BeginBlock does something like check input params before execute transactions,
// in rewardMgrPlugin it does nothing.
func (rmp *rewardMgrPlugin) BeginBlock(blockHash common.Hash, head *types.Header, state xcom.StateDB) error {
	return nil
}

// EndBlock will handle reward work, if it's time to settle, reward staking. Then reward worker
// for create new block, this is necessary. At last if current block is the last block at the end
// of year, increasing issuance.
func (rmp *rewardMgrPlugin) EndBlock(blockHash common.Hash, head *types.Header, state xcom.StateDB) error {

	blockNumber := head.Number.Uint64()
	log.Debug("begin to EndBlock in reward plugin", "hash", blockHash, "blockNumber", blockNumber)
	thisYear := xutil.CalculateYear(blockNumber)
	var lastYear uint32
	if thisYear != 0 {
		lastYear = thisYear - 1
	}

	stakingReward, newBlockReward := rmp.calculateExpectReward(thisYear, lastYear, state)
	log.Debug("show calculated data", "blockNumber", blockNumber, "total stkReward", stakingReward, "new block Reward", newBlockReward)

	if xutil.IsSettlementPeriod(blockNumber) {
		if err := rmp.rewardStaking(head, stakingReward, state); err != nil {
			return err
		}
		// set current to latest epoch
		currEpoch := xutil.CalculateEpoch(blockNumber)
		log.Info("Set latest epoch at settlement block", "blockNumber", blockNumber, "epoch", currEpoch)
		SetLatestEpoch(state, currEpoch)
	}

	if err := rmp.rewardNewBlock(head, newBlockReward, state); err != nil {
		return err
	}

	if xutil.IsYearEnd(blockNumber) {
		log.Info("ready to increase issuance", "blockNumber", blockNumber, "hash", head.Hash())
		rmp.increaseIssuance(thisYear, lastYear, state)
	}

	log.Debug("end to EndBlock in reward plugin")

	return nil
}

// Confirmed does nothing
func (rmp *rewardMgrPlugin) Confirmed(block *types.Block) error {
	return nil
}

// increaseIssuance used for increase issuance at the end of each year
func (rmp *rewardMgrPlugin) increaseIssuance(thisYear, lastYear uint32, state xcom.StateDB) {
	histIssuance := GetHistoryCumulativeIssue(state, lastYear)
	currIssuance := new(big.Int).Div(histIssuance, big.NewInt(40))
	if xcom.PlatONFoundationYear() > 0 && thisYear > xcom.PlatONFoundationYear() {
		tmpIssuance := new(big.Int).Div(currIssuance, big.NewInt(5))
		RewardManagerPoolIssuance := new(big.Int).Sub(currIssuance, tmpIssuance)
		halfIssuance := tmpIssuance.Div(tmpIssuance, big.NewInt(2))
		state.AddBalance(vm.RewardManagerPoolAddr, RewardManagerPoolIssuance)
		state.AddBalance(vm.CommunityDeveloperFoundation, halfIssuance)
		state.AddBalance(vm.PlatONFoundationAddress, halfIssuance)
	} else {
		devIssuance := new(big.Int).Div(currIssuance, big.NewInt(5))
		rewardIssuance := new(big.Int).Sub(currIssuance, devIssuance)
		state.AddBalance(vm.CommunityDeveloperFoundation, devIssuance)
		state.AddBalance(vm.RewardManagerPoolAddr, rewardIssuance)
	}
	// restore the cumulative issue at this year end
	histIssuance.Add(histIssuance, currIssuance)
	SetYearEndCumulativeIssue(state, thisYear, histIssuance)

	// restore the Balance of rewardMgrPool at this year end
	balance := state.GetBalance(vm.RewardManagerPoolAddr)
	SetYearEndBalance(state, thisYear, balance)
}

// rewardStaking used for reward staking at the settle block
func (rmp *rewardMgrPlugin) rewardStaking(head *types.Header, reward *big.Int, state xcom.StateDB) error {
	blockHash := head.Hash()
	blockNumber := head.Number.Uint64()

	log.Info("ready to reward staking", "blockNumber", blockNumber, "hash", blockHash,
		"epoch", xutil.CalculateEpoch(blockNumber), "total reward", reward)

	list, err := StakingInstance().GetVerifierList(blockHash, blockNumber, false)
	if err != nil {
		log.Error("get verifier list failed in rewardStaking", "blockNumber", blockNumber, "hash", blockHash)
		return err
	}
	rmp.rewardStakingByValidatorList(state, list, reward, blockNumber)
	return nil
}

func (rmp *rewardMgrPlugin) rewardStakingByValidatorList(state xcom.StateDB, list staking.ValidatorExQueue, reward *big.Int, blockNumber uint64) {
	validatorNum := int64(len(list))
	everyValidatorReward := new(big.Int).Div(reward, big.NewInt(validatorNum))
	log.Debug("get verifier list success", "listLen", len(list), "everyOneReward", everyValidatorReward, "list", list)
	for _, value := range list {
		addr := value.BenefitAddress
		if addr != vm.RewardManagerPoolAddr {
			log.Debug("rewarding staking", "blockNumber", blockNumber, "nodeId", value.NodeId.String(),
				"benefitAddress", addr.String(), "balance", everyValidatorReward)
			state.AddBalance(addr, everyValidatorReward)
		}
	}
	state.SubBalance(vm.RewardManagerPoolAddr, reward)
}

// rewardNewBlock used for reward new block. it returns coinbase and error
func (rmp *rewardMgrPlugin) rewardNewBlock(head *types.Header, reward *big.Int, state xcom.StateDB) error {

	rewardAddr := head.Coinbase
	if rewardAddr != vm.RewardManagerPoolAddr {

		log.Info("ready to reward new block", "blockNumber", head.Number.Uint64(), "hash", head.Hash(),
			"receive addr", rewardAddr.String(), "reward", reward)

		state.SubBalance(vm.RewardManagerPoolAddr, reward)
		state.AddBalance(rewardAddr, reward)
	}
	return nil
}

// calculateExpectReward used for calculate the stakingReward and newBlockReward that should be send in each corresponding period
func (rmp *rewardMgrPlugin) calculateExpectReward(thisYear, lastYear uint32, state xcom.StateDB) (*big.Int, *big.Int) {
	// get expected settlement epochs and new blocks per year first
	epochs := xutil.EpochsPerYear()
	blocks := xutil.CalcBlocksEachYear()
	log.Debug("[calculateExpectReward]epochs,blocks", "epochs", epochs, "blocks", blocks)

	lastYearBalance := GetYearEndBalance(state, lastYear)

	platONFoundationYear := xcom.PlatONFoundationYear()

	totalNewBlockReward, totalStakingReward := big.NewInt(0), big.NewInt(0)
	if platONFoundationYear > 0 && thisYear >= platONFoundationYear {
		rewardNewBlockRate := xcom.NewBlockRewardRate()
		if rewardNewBlockRate == 0 {
			totalNewBlockReward = new(big.Int).Div(lastYearBalance, big.NewInt(RewardNewBlockRate))
			totalStakingReward = new(big.Int).Sub(lastYearBalance, totalNewBlockReward)
		} else {
			tmp := new(big.Int).Mul(lastYearBalance, big.NewInt(int64(rewardNewBlockRate)))
			totalNewBlockReward.Div(tmp, big.NewInt(100))
			totalStakingReward.Sub(lastYearBalance, totalNewBlockReward)
		}
	} else {
		totalNewBlockReward = new(big.Int).Div(lastYearBalance, big.NewInt(RewardNewBlockRate))
		totalStakingReward = new(big.Int).Sub(lastYearBalance, totalNewBlockReward)
	}

	log.Debug("[calculateExpectReward]total reward to create new block and reward to staking", "totalNewBlockReward", totalNewBlockReward, "totalStakingReward", totalStakingReward)

	newBlockReward := new(big.Int).Div(totalNewBlockReward, big.NewInt(int64(blocks)))
	stakingReward := new(big.Int).Div(totalStakingReward, big.NewInt(int64(epochs)))

	log.Debug("[calculateExpectReward]reward to create new block and staking", "block", newBlockReward, "staking", stakingReward)

	return stakingReward, newBlockReward
}

// SetYearEndCumulativeIssue used for set historical cumulative increase at the end of the year
func SetYearEndCumulativeIssue(state xcom.StateDB, year uint32, total *big.Int) {
	yearEndIncreaseKey := reward.GetHistoryIncreaseKey(year)
	state.SetState(vm.RewardManagerPoolAddr, yearEndIncreaseKey, total.Bytes())
}

// GetHistoryCumulativeIssue used for get the cumulative issuance of a certain year in history
func GetHistoryCumulativeIssue(state xcom.StateDB, year uint32) *big.Int {
	var issue = new(big.Int)
	histIncreaseKey := reward.GetHistoryIncreaseKey(year)
	bIssue := state.GetState(vm.RewardManagerPoolAddr, histIncreaseKey)
	log.Trace("show history cumulative issue", "lastYear", year, "amount", issue.SetBytes(bIssue))
	return issue.SetBytes(bIssue)
}

func SetYearEndBalance(state xcom.StateDB, year uint32, balance *big.Int) {
	yearEndBalanceKey := reward.HistoryBalancePrefix(year)
	state.SetState(vm.RewardManagerPoolAddr, yearEndBalanceKey, balance.Bytes())
}

func GetYearEndBalance(state xcom.StateDB, year uint32) *big.Int {
	var balance = new(big.Int)
	yearEndBalanceKey := reward.HistoryBalancePrefix(year)
	bBalance := state.GetState(vm.RewardManagerPoolAddr, yearEndBalanceKey)
	log.Trace("show balance of reward pool at last year end", "lastYear", year, "amount", balance.SetBytes(bBalance))
	return balance.SetBytes(bBalance)
}
