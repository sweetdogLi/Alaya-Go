package network.platon.contracts.evm;

import com.alaya.abi.solidity.TypeReference;
import com.alaya.abi.solidity.datatypes.Function;
import com.alaya.abi.solidity.datatypes.Type;
import com.alaya.abi.solidity.datatypes.Utf8String;
import com.alaya.crypto.Credentials;
import com.alaya.protocol.Web3j;
import com.alaya.protocol.core.RemoteCall;
import com.alaya.protocol.core.methods.response.TransactionReceipt;
import com.alaya.tx.Contract;
import com.alaya.tx.TransactionManager;
import com.alaya.tx.gas.GasProvider;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the com.alaya.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.13.2.0.
 */
public class SpaceComplexity extends Contract {
    private static final String BINARY = "60806040526040518060400160405280600681526020017f71637869616f00000000000000000000000000000000000000000000000000008152506000908051906020019061004f929190610062565b5034801561005c57600080fd5b50610107565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a357805160ff19168380011785556100d1565b828001600101855582156100d1579182015b828111156100d05782518255916020019190600101906100b5565b5b5090506100de91906100e2565b5090565b61010491905b808211156101005760008160009055506001016100e8565b5090565b90565b610959806101166000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806306fdde0314610046578063380bc4ad146100c957806365becf9b146100f7575b600080fd5b61004e610125565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561008e578082015181840152602081019050610073565b50505050905090810190601f1680156100bb5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6100f5600480360360208110156100df57600080fd5b81019080803590602001909291905050506101c7565b005b6101236004803603602081101561010d57600080fd5b81019080803590602001909291905050506101ca565b005b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101bd5780601f10610192576101008083540402835291602001916101bd565b820191906000526020600020905b8154815290600101906020018083116101a057829003601f168201915b5050505050905090565b50565b60008090505b81811015610212576000600283816101e457fe5b0614156101fa576101f56000610216565b610205565b610204600061059d565b5b80806001019150506101d0565b5050565b7f510000000000000000000000000000000000000000000000000000000000000081600081546001816001161561010002031660029004811061025557fe5b8154600116156102745790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f43000000000000000000000000000000000000000000000000000000000000008160018154600181600116156101000203166002900481106102eb57fe5b81546001161561030a5790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f580000000000000000000000000000000000000000000000000000000000000081600281546001816001161561010002031660029004811061038157fe5b8154600116156103a05790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f490000000000000000000000000000000000000000000000000000000000000081600381546001816001161561010002031660029004811061041757fe5b8154600116156104365790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f41000000000000000000000000000000000000000000000000000000000000008160048154600181600116156101000203166002900481106104ad57fe5b8154600116156104cc5790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f4f0000000000000000000000000000000000000000000000000000000000000081600581546001816001161561010002031660029004811061054357fe5b8154600116156105625790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f01000000000000000000000000000000000000000000000000000000000000008404021790555050565b7f71000000000000000000000000000000000000000000000000000000000000008160008154600181600116156101000203166002900481106105dc57fe5b8154600116156105fb5790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f630000000000000000000000000000000000000000000000000000000000000081600181546001816001161561010002031660029004811061067257fe5b8154600116156106915790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f780000000000000000000000000000000000000000000000000000000000000081600281546001816001161561010002031660029004811061070857fe5b8154600116156107275790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f690000000000000000000000000000000000000000000000000000000000000081600381546001816001161561010002031660029004811061079e57fe5b8154600116156107bd5790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f610000000000000000000000000000000000000000000000000000000000000081600481546001816001161561010002031660029004811061083457fe5b8154600116156108535790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055507f6f000000000000000000000000000000000000000000000000000000000000008160058154600181600116156101000203166002900481106108ca57fe5b8154600116156108e95790600052602060002090602091828204019190065b601f036101000a81548160ff021916907f0100000000000000000000000000000000000000000000000000000000000000840402179055505056fea265627a7a7231582036702ebed91dde624f3afbb5faedf85facbaac50cbbe3e6f01ca8c4c3dd3e56f64736f6c63430005110032";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_TESTBIGOBJECTOFSTORAGE = "testBigObjectOfStorage";

    public static final String FUNC_TESTSTORAGE = "testStorage";

    protected SpaceComplexity(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected SpaceComplexity(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> testBigObjectOfStorage(BigInteger n) {
        final Function function = new Function(
                FUNC_TESTBIGOBJECTOFSTORAGE, 
                Arrays.<Type>asList(new com.alaya.abi.solidity.datatypes.generated.Uint256(n)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> testStorage(BigInteger n) {
        final Function function = new Function(
                FUNC_TESTSTORAGE, 
                Arrays.<Type>asList(new com.alaya.abi.solidity.datatypes.generated.Uint256(n)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SpaceComplexity> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(SpaceComplexity.class, web3j, credentials, contractGasProvider, BINARY,  "", chainId);
    }

    public static RemoteCall<SpaceComplexity> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(SpaceComplexity.class, web3j, transactionManager, contractGasProvider, BINARY,  "", chainId);
    }

    public static SpaceComplexity load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new SpaceComplexity(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static SpaceComplexity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new SpaceComplexity(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }
}
