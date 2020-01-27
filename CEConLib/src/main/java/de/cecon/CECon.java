package de.cecon;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CECon {

    private static CECon cecon = new CECon();

    public Web3j web3 = null;

    private Map<String, SmartContractConnection> smartContractConnectionBuffer;

    //Private Contructor -> Only one CECon Instance is allowed
    private CECon() {
        smartContractConnectionBuffer = new HashMap<>();
    }

    /**
     * Gets the current or creates a new CECon Instance
     * @return CECon Instance
     */
    public static CECon getInstance() {
        if(cecon == null)
            cecon = new CECon();
        return cecon;
    }

    public boolean connectToEthereumClient(String host, String port) throws IOException {
        return connectToEthereumClient(host + ":" + port);
    }

    public boolean connectToEthereumClient(String host, int port) throws IOException {
        return connectToEthereumClient(host + ":" + port);
    }

    /**
     * Connects to a running Ethereum Client for example Geth or Infuria
     * @param url the Url to the running Ethereum Client
     * @return true if connected successfully, false if connection failed
     */
    public boolean connectToEthereumClient(String url) throws IOException {
        web3 = Web3j.build(new HttpService(url));
        Web3ClientVersion clientVersion = null;

        clientVersion = web3.web3ClientVersion().send();

        if(clientVersion.hasError()) {
            System.out.println("Client Error: " + clientVersion.getError());
            return false;
        }

        System.out.println("Connected to Client: " + clientVersion.getWeb3ClientVersion());

        return true;
    }

    /**
     * Sets a new SmartContractConnection that can be accessed again with the instanceName
     * @param instanceName sets the name of the instance.
     * @param smartContractWrapper sets the Smart Contract Wrapper of the instance.
     * @param wallet sets the WalletConnection. Must be the same as the wallet that loaded the Smart Contract
     */
    public void setSmartContractConnection(String instanceName, Object smartContractWrapper, WalletConnection wallet) {
        SmartContractConnection scConnection = new SmartContractConnection();
        scConnection.setSmartContractInstance(smartContractWrapper);
        scConnection.setWallet(wallet);
        smartContractConnectionBuffer.put(instanceName, scConnection);
    }

    /**
     * Gets the WalletConnection of the instanceName
     * @param instanceName the name of the SmartContractConnection Instance
     * @return the WalletConnection if the instanceName exists, otherwise returns null
     */
    public WalletConnection getWalletConnectionFromInstance(String instanceName) {
        if(smartContractConnectionBuffer.containsKey(instanceName))
            return smartContractConnectionBuffer.get(instanceName).getWallet();
        else
            return null;
    }

    /**
     * Gets the SmartContractWrapper of the instanceName
     * @param instanceName the name of the SmartContractConnection Instance
     * @return the SmartContractWrapper if the instanceName exists, otherwise returns null
     */
    public Object getSmartContractFromInstance(String instanceName) {
        if(smartContractConnectionBuffer.containsKey(instanceName))
            return smartContractConnectionBuffer.get(instanceName).getSmartContractInstance();
        else
            return null;
    }

    /**
     * Calls the given function of a Smart Contract and prints the TransactionHash after completing
     * @param function the Smart Contract function
     * @return the TransactionReceipt of the call, if needed
     */
    public TransactionReceipt callFunction(RemoteCall<TransactionReceipt> function) throws Exception {
        TransactionReceipt tr = function.send();
        System.out.println("Transaction completed: " + tr.getTransactionHash());
        return tr;
    }

    /**
     * Gets the current balance of the given wallet
     * @param wallet the WalletConnection
     * @return the current amount of Ether of the given wallet
     */
    public BigDecimal getBalance(WalletConnection wallet) throws IOException {
        EthGetBalance ethGetBalance = web3.ethGetBalance(wallet.getAddress(), DefaultBlockParameterName.LATEST).send();
        return Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
    }

    /**
     * Sends a amount of ether from a wallet to an address
     * @param wallet the WalletConnection from which the Ether will be sent
     * @param toAddress the address to which the Ether will be sent
     * @param amount the amount of Ether to be sent
     * @return the TransactionReceipt of the call, if needed
     */
    public TransactionReceipt sendEther(WalletConnection wallet, String toAddress, long amount) throws Exception {
        TransactionReceipt tr = Transfer.sendFunds(
                cecon.web3, wallet.getCredentials(), toAddress,
                BigDecimal.valueOf(amount), Convert.Unit.ETHER)
                .send();
        return tr;
    }

    /**
     * Disconnects from the Ethereum Client
     */
    public void disconnect() {
        web3.shutdown();
        System.out.println("Disconnected from Client!");
    }
}
