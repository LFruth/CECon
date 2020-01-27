package de.cecon.demo.hersteller;

import de.cecon.CECon;
import de.cecon.WalletConnection;
import de.cecon.demo.ethereum.CeconDemoContract;
import de.cecon.demo.ethereum.Helper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InitialiseCECon implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        CECon cecon = CECon.getInstance();
        //Verbindung zum Ethereum Client herstellen
        cecon.connectToEthereumClient("http://localhost:8484");

        WalletConnection herstellerWallet = new WalletConnection("herstellerpw",Helper.WALLETS_PATH + "herstellerwallet.json");
        CeconDemoContract contract = CeconDemoContract.load(Helper.SMART_CONTRACT_ADDRESS, cecon.web3, herstellerWallet.getCredentials(), Helper.GAS_PRICE, Helper.GAS_LIMIT);

        cecon.setSmartContractConnection("hersteller", contract, herstellerWallet);
    }

}
