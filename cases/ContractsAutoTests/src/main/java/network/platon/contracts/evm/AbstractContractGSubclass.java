package network.platon.contracts.evm;

import com.alaya.abi.solidity.TypeReference;
import com.alaya.abi.solidity.datatypes.Function;
import com.alaya.abi.solidity.datatypes.Type;
import com.alaya.abi.solidity.datatypes.generated.Int256;
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
public class AbstractContractGSubclass extends Contract {
    private static final String BINARY = "60806040526000805534801561001457600080fd5b5060f2806100236000396000f3fe6080604052348015600f57600080fd5b5060043610603c5760003560e01c8063262a9dff14604157806335f99d4814605d578063d1eef376146088575b600080fd5b604760a4565b6040518082815260200191505060405180910390f35b608660048036036020811015607157600080fd5b810190808035906020019092919050505060aa565b005b608e60b4565b6040518082815260200191505060405180910390f35b60005481565b8060008190555050565b6000805490509056fea265627a7a7231582080b9d383e7d82ac4aa015c2b769fd42b29fc49f866624ed74252596e66c6589064736f6c63430005110032";

    public static final String FUNC_AINTERAGE = "aInterAge";

    public static final String FUNC_AGE = "age";

    public static final String FUNC_SETINTERAGE = "setInterAge";

    protected AbstractContractGSubclass(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected AbstractContractGSubclass(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public RemoteCall<BigInteger> aInterAge() {
        final Function function = new Function(FUNC_AINTERAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> age() {
        final Function function = new Function(FUNC_AGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setInterAge(BigInteger v) {
        final Function function = new Function(
                FUNC_SETINTERAGE, 
                Arrays.<Type>asList(new Int256(v)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<AbstractContractGSubclass> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(AbstractContractGSubclass.class, web3j, credentials, contractGasProvider, BINARY,  "", chainId);
    }

    public static RemoteCall<AbstractContractGSubclass> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(AbstractContractGSubclass.class, web3j, transactionManager, contractGasProvider, BINARY,  "", chainId);
    }

    public static AbstractContractGSubclass load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new AbstractContractGSubclass(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static AbstractContractGSubclass load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new AbstractContractGSubclass(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }
}
