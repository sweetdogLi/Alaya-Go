package network.platon.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.GasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.9.1.0-SNAPSHOT.
 */
public class AssertHandle extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610172806100206000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80638c671e0a14610067578063ad92212f14610071578063afcd320e1461007b578063cda0a5eb146100a9578063f25e0471146100fc578063f81cf6db14610106575b600080fd5b61006f610110565b005b610079610112565b005b6100a76004803603602081101561009157600080fd5b8101908080359060200190929190505050610114565b005b6100d8600480360360208110156100bf57600080fd5b81019080803560000b9060200190929190505050610121565b604051808260038111156100e857fe5b60ff16815260200191505060405180910390f35b610104610139565b005b61010e61013b565b005b565b565b600a811061011e57fe5b50565b60008160000b600381111561013257fe5b9050919050565b565b56fea265627a7a72315820442c0ff9025fd63ae132e0df1350f1cc90178c417937bd67be8d4f20d830930d64736f6c634300050d0032";

    public static final String FUNC_BINARYMOVEMINUSEXCEPTION = "binaryMoveMinusException";

    public static final String FUNC_DIVIDENDZEROEXCEPTION = "dividendZeroException";

    public static final String FUNC_INTCHANGEEXCEPTION = "intChangeException";

    public static final String FUNC_NOOUTOFBOUNDSEXCEPTION = "noOutOfBoundsException";

    public static final String FUNC_OUTOFBOUNDSEXCEPTION = "outOfBoundsException";

    public static final String FUNC_PARAMEXCEPTION = "paramException";

    @Deprecated
    protected AssertHandle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AssertHandle(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AssertHandle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AssertHandle(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> binaryMoveMinusException() {
        final Function function = new Function(
                FUNC_BINARYMOVEMINUSEXCEPTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> dividendZeroException() {
        final Function function = new Function(
                FUNC_DIVIDENDZEROEXCEPTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> intChangeException(BigInteger param) {
        final Function function = new Function(
                FUNC_INTCHANGEEXCEPTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int8(param)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> noOutOfBoundsException() {
        final Function function = new Function(
                FUNC_NOOUTOFBOUNDSEXCEPTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> outOfBoundsException() {
        final Function function = new Function(
                FUNC_OUTOFBOUNDSEXCEPTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> paramException(BigInteger param) {
        final Function function = new Function(
                FUNC_PARAMEXCEPTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<AssertHandle> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        return deployRemoteCall(AssertHandle.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AssertHandle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AssertHandle.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AssertHandle> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        return deployRemoteCall(AssertHandle.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AssertHandle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AssertHandle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static AssertHandle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssertHandle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AssertHandle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AssertHandle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AssertHandle load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        return new AssertHandle(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AssertHandle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        return new AssertHandle(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
