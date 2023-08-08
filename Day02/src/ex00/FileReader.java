package ex00;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface FileReader {
    public Map<String, String> read(File file) throws IOException;
}
