package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class FileReaderSignatures implements FileReader {
    @Override
    public Map<String, String> read(File file) {
        Map<String, String> multiHashMapSignatures = new HashMap<>();
        try (FileInputStream inputStream = new FileInputStream(file);) {
            Scanner fileScanner = new Scanner(inputStream);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                multiHashMapSignatures.put(tokens[1].replaceAll("\\s+", ""), tokens[0].trim());
            }
            // ?
            System.out.println(multiHashMapSignatures);
            inputStream.close();
            fileScanner.close();
        } catch (Throwable error) {
            System.out.println(error.getMessage());
            System.exit(-1);
        }
        return multiHashMapSignatures;
    }
}
