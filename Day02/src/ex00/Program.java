package ex00;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {
    static private final String SIGNATURES_FILE = "src/ex00/signatures.txt";

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReaderSignatures();
            File file = new File(SIGNATURES_FILE);
            Map<String, String> mapSignatures = fileReader.read(file);

            Scanner consoleScanner = new Scanner(System.in);
            String inputFilePath = consoleScanner.nextLine();
            while (!inputFilePath.equals("42")) {
                String fileSignature = FileSignatureAnalyzer.analyzeFileSignatures(mapSignatures, new File(inputFilePath));
                inputFilePath = consoleScanner.nextLine();
            }
            consoleScanner.close();
        } catch (Throwable error) {
            System.out.println(error.getMessage());
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
