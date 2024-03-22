package ex00;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Map<String, String> mapSignatures = FileReaderSignatures.read();

        try (Scanner consoleScanner = new Scanner(System.in);) {
            String inputFilePath = consoleScanner.nextLine();

            while (!inputFilePath.equals("42")) {
                try {
                    String fileSignature = FileSignatureAnalyzer.analyzeFileSignatures(mapSignatures, new File(inputFilePath));

                    if (fileSignature.equals("UNDEFINED")) {
                        System.out.println("UNDEFINED");
                    } else {
                        System.out.println("PROCESSED");
                        FileWriterSignature.writeExtension(fileSignature);
                    }

                } catch (Throwable error) {
                    System.err.println(error.getMessage());
                }
                inputFilePath = consoleScanner.nextLine();
            }
        }
    }
}
