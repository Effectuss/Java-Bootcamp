package ex00;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSignature {
    static private final String SEPARATOR = File.separator;
    static private final String RESULT_FILE = "src" + SEPARATOR + "ex00" + SEPARATOR + "result.txt";

    static public void writeExtension(String extension) {
        try {
            FileWriter fileWriter = new FileWriter(RESULT_FILE, true);
            fileWriter.write(extension + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
