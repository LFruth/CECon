package de.cecon;

import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.io.IOException;

public class CEConUtils {

    public static DefaultBlockParameter EARLIEST_BLOCK = DefaultBlockParameterName.EARLIEST;
    public static DefaultBlockParameter LATEST_BLOCK = DefaultBlockParameterName.LATEST;

    /**
     * Compiles the given Smart Contract (.sol) file to .bin and .abi
     * @param solcPath the path to the solc.exe file
     * @param contractPath the path to the Smart Contract (.sol)
     * @param outputPath the path where the .bin and .abi file will be created
     */
    public static void CompileSmartContract(String solcPath, String contractPath, String outputPath) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(solcPath + " " + contractPath + " --bin --abi -o " + outputPath);
    }

}
