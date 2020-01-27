package de.cecon.demo.ethereum;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class CeconDemoContract extends Contract {
    private static final String BINARY = "608060405273f9dc601481432570ef01a4ce87c06e13dc1fbe116000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507355ef3f5fea1ba9db414a87bb6c4f1ec4176e34bf600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507321ae548a03c2e68fe3129162def065d55895f26e600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600355662f40478f83400060055566354a6ba7a18000600655664a9b6384488000600755665c5edcbc29000060085534801561013f57600080fd5b506108fc8061014f6000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063031d37a2146100a957806388685cd91461016c578063979d2027146101995780639ce545e4146101e4578063bb60e5f61461020f578063c949cb831461023a578063d8f4ad371461025a578063eab5c61014610287578063f0d5758a146102b2578063f42564c7146102df575b600080fd5b3480156100b557600080fd5b5061016a60048036038101908080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061030a565b005b34801561017857600080fd5b50610197600480360381019080803590602001909291905050506104fa565b005b3480156101a557600080fd5b506101e260048036038101908080359060200190929190803590602001909291908035906020019092919080359060200190929190505050610626565b005b3480156101f057600080fd5b506101f96106d7565b6040518082815260200191505060405180910390f35b34801561021b57600080fd5b506102246106dd565b6040518082815260200191505060405180910390f35b610258600480360381019080803590602001909291905050506106e3565b005b34801561026657600080fd5b5061028560048036038101908080359060200190929190505050610798565b005b34801561029357600080fd5b5061029c61082e565b6040518082815260200191505060405180910390f35b3480156102be57600080fd5b506102dd60048036038101908080359060200190929190505050610834565b005b3480156102eb57600080fd5b506102f46108ca565b6040518082815260200191505060405180910390f35b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561036757600080fd5b60009050600085141561038057826005540290506103be565b600185141561039557826006540290506103bd565b60028514156103aa57826007540290506103bc565b60038514156103bb57826008540290505b5b5b5b7fc82149f48d1c6a45f1e7d591fe4c4c0f33d4a5c670eba71803319d4386ae4e4360035485858585604051808681526020018060200185815260200180602001848152602001838103835287818151815260200191508051906020019080838360005b8381101561043c578082015181840152602081019050610421565b50505050905090810190601f1680156104695780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156104a2578082015181840152602081019050610487565b50505050905090810190601f1680156104cf5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390a16003600081548092919060010191905055505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561055557600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc60046000848152602001908152602001600020549081150290604051600060405180830381858888f193505050501580156105d0573d6000803e3d6000fd5b507fc8ef479acdbffd729d96117b951d1c3ee85d98ef42699aec806bc4f0b522461d816004600084815260200190815260200160002054604051808381526020018281526020019250505060405180910390a150565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561068257600080fd5b7fdde03068a64edd98d1f30809df2e973beb004b921171c108be18739a55cb38d1848484846040518085815260200184815260200183815260200182815260200194505050505060405180910390a150505050565b60085481565b60075481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561073e57600080fd5b3460046000838152602001908152602001600020819055507f8b9a69e4cce4a00998a4f70901e28458c5b71a5bba8c8be4544ed5250e01007f8134604051808381526020018281526020019250505060405180910390a150565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156107f457600080fd5b7f17a57da0749b80a2d00ae04030987107752af8fc22484b575b84b7256d8c11bb816040518082815260200191505060405180910390a150565b60065481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561089057600080fd5b7f8ce8ef19e3542b8f2c4a968a4e8359ded2d9c95c3a852d77bed7a605a98affb4816040518082815260200191505060405180910390a150565b600554815600a165627a7a72305820c4d331d9a38d823519c4ea8b5f33e174527c7c02c542f141b43fe9bc3df5299a0029";

    public static final String FUNC_ADDNEWORDER = "addNewOrder";

    public static final String FUNC_RELEASEPAYMENT = "releasePayment";

    public static final String FUNC_ADDSHIPPINGDETAILS = "addShippingDetails";

    public static final String FUNC_OAKWOODCOSTS = "oakwoodCosts";

    public static final String FUNC_ASHWOODCOSTS = "ashwoodCosts";

    public static final String FUNC_ADDPAYMENT = "addPayment";

    public static final String FUNC_ADDSHIPPINGCONFIRMATION = "addShippingConfirmation";

    public static final String FUNC_BEECHWOODCOSTS = "beechwoodCosts";

    public static final String FUNC_ADDPENDINGORDER = "addPendingOrder";

    public static final String FUNC_PINEWOODCOSTS = "pinewoodCosts";

    public static final Event NEWORDER_EVENT = new Event("NewOrder", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PENDINGORDER_EVENT = new Event("PendingOrder", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SHIPPINGCONFIRMATION_EVENT = new Event("ShippingConfirmation", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SHIPPINGDETAILS_EVENT = new Event("ShippingDetails", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYEDORDER_EVENT = new Event("PayedOrder", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYMENTRELEASED_EVENT = new Event("PaymentReleased", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected CeconDemoContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CeconDemoContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addNewOrder(BigInteger productID, String productName, BigInteger productCount, String orderDetails) {
        final Function function = new Function(
                FUNC_ADDNEWORDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int256(productID), 
                new Utf8String(productName),
                new Uint256(productCount),
                new Utf8String(orderDetails)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> releasePayment(BigInteger orderID) {
        final Function function = new Function(
                FUNC_RELEASEPAYMENT,
                Arrays.<Type>asList(new Uint256(orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addShippingDetails(BigInteger orderID, BigInteger year, BigInteger month, BigInteger day) {
        final Function function = new Function(
                FUNC_ADDSHIPPINGDETAILS,
                Arrays.<Type>asList(new Uint256(orderID),
                new Uint256(year),
                new Uint256(month),
                new Uint256(day)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> oakwoodCosts() {
        final Function function = new Function(FUNC_OAKWOODCOSTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ashwoodCosts() {
        final Function function = new Function(FUNC_ASHWOODCOSTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addPayment(BigInteger orderID, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDPAYMENT,
                Arrays.<Type>asList(new Uint256(orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> addShippingConfirmation(BigInteger orderID) {
        final Function function = new Function(
                FUNC_ADDSHIPPINGCONFIRMATION,
                Arrays.<Type>asList(new Uint256(orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> beechwoodCosts() {
        final Function function = new Function(FUNC_BEECHWOODCOSTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addPendingOrder(BigInteger orderID) {
        final Function function = new Function(
                FUNC_ADDPENDINGORDER,
                Arrays.<Type>asList(new Uint256(orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> pinewoodCosts() {
        final Function function = new Function(FUNC_PINEWOODCOSTS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<NewOrderEventResponse> getNewOrderEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NEWORDER_EVENT, transactionReceipt);
        ArrayList<NewOrderEventResponse> responses = new ArrayList<NewOrderEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NewOrderEventResponse typedResponse = new NewOrderEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.productName = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.productCount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.orderDetails = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewOrderEventResponse> newOrderEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewOrderEventResponse>() {
            @Override
            public NewOrderEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NEWORDER_EVENT, log);
                NewOrderEventResponse typedResponse = new NewOrderEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.productName = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.productCount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.orderDetails = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewOrderEventResponse> newOrderEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWORDER_EVENT));
        return newOrderEventObservable(filter);
    }

    public List<PendingOrderEventResponse> getPendingOrderEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PENDINGORDER_EVENT, transactionReceipt);
        ArrayList<PendingOrderEventResponse> responses = new ArrayList<PendingOrderEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PendingOrderEventResponse typedResponse = new PendingOrderEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PendingOrderEventResponse> pendingOrderEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, PendingOrderEventResponse>() {
            @Override
            public PendingOrderEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PENDINGORDER_EVENT, log);
                PendingOrderEventResponse typedResponse = new PendingOrderEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<PendingOrderEventResponse> pendingOrderEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PENDINGORDER_EVENT));
        return pendingOrderEventObservable(filter);
    }

    public List<ShippingConfirmationEventResponse> getShippingConfirmationEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SHIPPINGCONFIRMATION_EVENT, transactionReceipt);
        ArrayList<ShippingConfirmationEventResponse> responses = new ArrayList<ShippingConfirmationEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ShippingConfirmationEventResponse typedResponse = new ShippingConfirmationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ShippingConfirmationEventResponse> shippingConfirmationEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ShippingConfirmationEventResponse>() {
            @Override
            public ShippingConfirmationEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SHIPPINGCONFIRMATION_EVENT, log);
                ShippingConfirmationEventResponse typedResponse = new ShippingConfirmationEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ShippingConfirmationEventResponse> shippingConfirmationEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SHIPPINGCONFIRMATION_EVENT));
        return shippingConfirmationEventObservable(filter);
    }

    public List<ShippingDetailsEventResponse> getShippingDetailsEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SHIPPINGDETAILS_EVENT, transactionReceipt);
        ArrayList<ShippingDetailsEventResponse> responses = new ArrayList<ShippingDetailsEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ShippingDetailsEventResponse typedResponse = new ShippingDetailsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.year = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.month = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.day = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ShippingDetailsEventResponse> shippingDetailsEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ShippingDetailsEventResponse>() {
            @Override
            public ShippingDetailsEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SHIPPINGDETAILS_EVENT, log);
                ShippingDetailsEventResponse typedResponse = new ShippingDetailsEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.year = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.month = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.day = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ShippingDetailsEventResponse> shippingDetailsEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SHIPPINGDETAILS_EVENT));
        return shippingDetailsEventObservable(filter);
    }

    public List<PayedOrderEventResponse> getPayedOrderEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PAYEDORDER_EVENT, transactionReceipt);
        ArrayList<PayedOrderEventResponse> responses = new ArrayList<PayedOrderEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PayedOrderEventResponse typedResponse = new PayedOrderEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PayedOrderEventResponse> payedOrderEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, PayedOrderEventResponse>() {
            @Override
            public PayedOrderEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAYEDORDER_EVENT, log);
                PayedOrderEventResponse typedResponse = new PayedOrderEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<PayedOrderEventResponse> payedOrderEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYEDORDER_EVENT));
        return payedOrderEventObservable(filter);
    }

    public List<PaymentReleasedEventResponse> getPaymentReleasedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PAYMENTRELEASED_EVENT, transactionReceipt);
        ArrayList<PaymentReleasedEventResponse> responses = new ArrayList<PaymentReleasedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PaymentReleasedEventResponse typedResponse = new PaymentReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<PaymentReleasedEventResponse> paymentReleasedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, PaymentReleasedEventResponse>() {
            @Override
            public PaymentReleasedEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAYMENTRELEASED_EVENT, log);
                PaymentReleasedEventResponse typedResponse = new PaymentReleasedEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.orderCosts = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<PaymentReleasedEventResponse> paymentReleasedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTRELEASED_EVENT));
        return paymentReleasedEventObservable(filter);
    }

    public static RemoteCall<CeconDemoContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CeconDemoContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CeconDemoContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CeconDemoContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static CeconDemoContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CeconDemoContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CeconDemoContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CeconDemoContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NewOrderEventResponse {
        public Log log;

        public BigInteger orderID;

        public String productName;

        public BigInteger productCount;

        public String orderDetails;

        public BigInteger orderCosts;
    }

    public static class PendingOrderEventResponse {
        public Log log;

        public BigInteger orderID;
    }

    public static class ShippingConfirmationEventResponse {
        public Log log;

        public BigInteger orderID;
    }

    public static class ShippingDetailsEventResponse {
        public Log log;

        public BigInteger orderID;

        public BigInteger year;

        public BigInteger month;

        public BigInteger day;
    }

    public static class PayedOrderEventResponse {
        public Log log;

        public BigInteger orderID;

        public BigInteger orderCosts;
    }

    public static class PaymentReleasedEventResponse {
        public Log log;

        public BigInteger orderID;

        public BigInteger orderCosts;
    }
}
