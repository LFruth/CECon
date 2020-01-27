package de.cecon.demo.ethereum;

import java.math.BigInteger;

public class Helper {

    public static final String SMART_CONTRACT_ADDRESS = "0x11d2d6bb7e1bfb84a605d5353e8f82bca8f16c47";

    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

    public static final String WALLETS_PATH = System.getProperty("jboss.home.dir") + "/standalone/data/Wallets/";

    public static final String BILL_PATH = System.getProperty("jboss.home.dir") + "/standalone/data/Bills/";
    public static final String BILL_NAME = "Bill_OrderID_";

}
