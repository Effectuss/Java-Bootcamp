package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class FileReaderSignatures {

    static private final String SEPARATOR = File.separator;
    static private final String SIGNATURES_FILE = "src" + SEPARATOR + "ex00" + SEPARATOR + "signatures.txt";

    static public Map<String, String> read(){
        Map<String, String> multiHashMapSignatures = new HashMap<>();
        try (FileInputStream inputStream = new FileInputStream(SIGNATURES_FILE);) {
            Scanner fileScanner = new Scanner(inputStream);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                multiHashMapSignatures.put(tokens[1].replaceAll("\\s+", ""), tokens[0].trim());
            }
            inputStream.close();
            fileScanner.close();
        } catch (Throwable error) {
            System.out.println(error.getMessage());
            System.exit(-1);
        }
        return multiHashMapSignatures;
    }
}
