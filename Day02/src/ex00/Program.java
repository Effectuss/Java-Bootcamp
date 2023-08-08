package ex00;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try {
            Map<String, String> mapSignatures = FileReaderSignatures.read();

            Scanner consoleScanner = new Scanner(System.in);
            String inputFilePath = consoleScanner.nextLine();

            while (!inputFilePath.equals("42")) {
                String fileSignature = FileSignatureAnalyzer.analyzeFileSignatures(mapSignatures, new File(inputFilePath));
                if (fileSignature.equals("UNDEFINED")) {
                    System.out.println("UNDEFINED");
                } else {
                    System.out.println("PROCESSED");
                    FileWriterSignature.writeExtension(fileSignature);
                }
                inputFilePath = consoleScanner.nextLine();
            }

            consoleScanner.close();
        } catch (Throwable error) {
            System.out.println(error.getMessage());
            System.exit(-1);
        }
    }
}
