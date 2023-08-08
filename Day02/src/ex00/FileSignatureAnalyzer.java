package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileSignatureAnalyzer {
    public static String analyzeFileSignatures(Map<String, String> mapSignatures, File file) {
        String signature = "";
        int maxLengthSignature = findMaxLenOfSignatureInByte(mapSignatures);
        try {
            byte[] fileBytes = Files.readAllBytes(Path.of(file.getPath()));
            int bytesToRead = Math.min(fileBytes.length, maxLengthSignature);
            byte[] fileSignature = new byte[maxLengthSignature];
            System.arraycopy(fileBytes, 0, fileSignature, 0, bytesToRead);
            String fileSignatureHex = bytesToHex(fileSignature);
            signature = findSignature(mapSignatures, fileSignatureHex);
            System.out.println(signature);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return signature;
    }

    private static int findMaxLenOfSignatureInByte(Map<String, String> mapSignatures) {
        int length = 0;
        for (String key : mapSignatures.keySet()) {
            if (key.length() > length) {
                length = key.length();
            }
        }
        return length / 2;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    private static String findSignature(Map<String, String> mapSignatures, String fileSignatureHex) {
        StringBuilder sb = new StringBuilder(fileSignatureHex);
        while (!sb.isEmpty()) {
            if (mapSignatures.containsKey(sb.toString())) {
                return mapSignatures.get(sb.toString());
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return "UNDEFINED";
    }

}
