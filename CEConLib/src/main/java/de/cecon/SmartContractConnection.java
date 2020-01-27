package de.cecon;

class SmartContractConnection {

    private Object smartContractInstance;
    private WalletConnection wallet;

    protected void setSmartContractInstance(Object scInstance) {
        smartContractInstance = scInstance;
    }

    protected Object getSmartContractInstance() {
        return smartContractInstance;
    }

    protected void setWallet(WalletConnection wallet) {
        this.wallet = wallet;
    }

    protected WalletConnection getWallet() {
        return wallet;
    }

}
