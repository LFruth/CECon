package de.cecon;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class WalletConnection {

    private Credentials credentials;

    /**
     * Instantiates new WalletConnection Instance
     * @param pw password of the wallet
     * @param path path to the wallet file
     */
    public WalletConnection(String pw, String path) {
        loadWallet(pw, path);
    }

    /**
     * Creates and loads a new wallet file
     * @param pw password of the new wallet
     * @param path path where the wallet file is going to be saved
     * @return filepath to the new wallet file
     */
    public String createNewWallet(String pw, String path) {
        String fileName = "";

        try {
            fileName = WalletUtils.generateNewWalletFile(pw,
                    new File(path),
                    false);
        } catch(Exception e) {
            System.out.println("Exception while creating Wallet: " + e);
        } finally {
            loadWallet(pw,  path  + "/" + fileName);
        }

        return fileName;
    }

    /**
     * Loads a wallet from a file
     * @param pw password of the new wallet
     * @param path path to the wallet file
     */
    private void loadWallet(String pw, String path) {
        Credentials creds = null;

        try {
            creds = WalletUtils.loadCredentials(
                    pw,
                    path);
        } catch(Exception e) {
            System.out.println("Exception while loading Credentials: " + e);
        } finally {
            System.out.println("Wallet loaded: " + creds.getAddress());
        }

        credentials = creds;
    }

    /**
     * @return the Credentials of the loaded wallet
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * @return the public address of the loaded wallet
     */
    public String getAddress() { return credentials.getAddress(); }

}
