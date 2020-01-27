package de.cecon.demo.ethereum;

import de.cecon.CECon;
import de.cecon.CEConUtils;
import de.cecon.WalletConnection;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) throws Exception {
        //Compile
        /*
        CEConUtils.CompileSmartContract("C:/Users/leonf/solidity-windows/solc",
                "C:/Users/leonf/Dropbox/Bachelorarbeit/Prototyp/Demo_Contract.sol",
                "C:/Users/leonf/Dropbox/Bachelorarbeit/Prototyp/");
        */


        CECon cecon = CECon.getInstance();
        cecon.connectToEthereumClient("https://rinkeby.infura.io/v3/01a53e69902b4a97bae866883d6d8a67");
        WalletConnection hw = new WalletConnection("herstellerpw", "C:/Wallets/herstellerwallet.json");
        System.out.println("balance hersteller: " + cecon.getBalance(hw));

        //cecon.sendEther(hw, "0x21ae548a03c2e68fe3129162def065d55895f26e", 1);

        //CeconDemoContract contract = CeconDemoContract.deploy(cecon.web3, hw.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT).send();
        //System.out.println("Deployed: " + contract.getContractAddress());

        /*
        CeconDemoContract contract = CeconDemoContract.load(Helper.SMART_CONTRACT_ADDRESS, cecon.web3, hw.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT);
        System.out.println("Loaded: " + contract.getContractAddress());

        List<CeconDemoContract.NewOrderEventResponse> orders = new ArrayList<>();

        Subscription orderSub = contract.newOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(re -> orders.add(re));

        while(orders.size() == 0) {

        }
        for(CeconDemoContract.NewOrderEventResponse resp : orders) {
            System.out.println("Order: " + resp.orderID.longValue());
        }

        */


        /*
        CeconDemoContract contract = CeconDemoContract.deploy(cecon.web3, hw.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT).send();
        System.out.println("Contract: " + contract.getContractAddress());

        /*

        CECon cecon = CECon.getInstance();
        cecon.connectToEthereumClient("http://localhost:8484");

        WalletConnection hw = new WalletConnection("herstellerpw", "C:/Wallets/herstellerwallet.json");
        System.out.println("balance hersteller: " + cecon.getBalance(hw));
        WalletConnection lw = new WalletConnection("lieferantpw", "C:/Wallets/lieferantwallet.json");
        System.out.println("balance lieferant: " + cecon.getBalance(lw));
        WalletConnection logw = new WalletConnection("logistikpw", "C:/Wallets/logistikwallet.json");
        System.out.println("balance logistic: " + cecon.getBalance(logw));

        CeconDemoContract contract = CeconDemoContract.load(Helper.SMART_CONTRACT_ADDRESS, cecon.web3, logw.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT);

        cecon.callFunction(contract.shippingConfirmation(BigInteger.valueOf(1)));

        List<Long> shippingConfirmationList = new ArrayList<>();

        Subscription confirmationSubscription = contract.shipOrderEventEventObservable(CEConUtils.EARLIEST_BLOCK,
                CEConUtils.EARLIEST_BLOCK).subscribe(confirmation -> shippingConfirmationList.add(confirmation.orderID.longValue()));
        System.out.println("Subscribed!");
        while(shippingConfirmationList.size() == 0) {

        }
        for(Long l : shippingConfirmationList) {
            System.out.println("Shipped Orderid: " + l);
        }
        System.out.println("Orders?");
        */
        /*
        CeconDemoContract contract = CeconDemoContract.load(Helper.SMART_CONTRACT_ADDRESS, cecon.web3, hw.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT);
        System.out.println(contract.getContractAddress());

        List<CeconDemoContract.NewOrderEventEventResponse> orders = new ArrayList<>();
        List<Long> payedIds = new ArrayList<>();

        Subscription orderSub = contract.newOrderEventEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(re -> orders.add(re));

        Subscription payedSub = contract.paymentReleasedEventEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(re -> payedIds.add(re.orderID.longValue()));

        for(CeconDemoContract.NewOrderEventEventResponse resp : orders) {
            if(!payedIds.contains(resp.orderID)) {
                cecon.callFunction(contract.releasePayment(resp.orderID));
                System.out.println("Order: " + resp.orderID + " payed");
            }
        }
        */
        /*
        TransactionReceipt tr = Transfer.sendFunds(
                cecon.web3, lw.getCredentials(), "0xf9dc601481432570ef01a4ce87c06e13dc1fbe11",
                BigDecimal.valueOf(5), Convert.Unit.ETHER)
                .send();
        System.out.println("Sent Ether: " + tr.getTransactionHash());
        */
    }

}
