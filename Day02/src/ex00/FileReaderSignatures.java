package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class FileReaderSignatures implements FileReader {
    @Override
    public Map<String, List<String>> read(File file) {
         Map<String, List<String>> mapSignatures = new HashMap<>();
        try (FileInputStream inputStream = new FileInputStream(file);) {
            Scanner fileScanner = new Scanner(inputStream);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(",");
                mapSignatures.computeIfAbsent(tokens[0].trim(), k -> new ArrayList<>()).add(tokens[1].trim());
            }
            fileScanner.close();
        } catch (Throwable error) {
            System.out.println(error.getMessage());
            System.exit(-1);
        }
        return mapSignatures;
    }
}
