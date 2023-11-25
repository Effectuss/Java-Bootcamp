package ex03.filedownloader;

import ex03.filedownloader.exception.MultithreadedFileDownloaderException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedFileDownloader {
    private final int numberOfThreads;

    public MultithreadedFileDownloader(int numberOfThreads) {
        if (!isValidNumberOfThreads(numberOfThreads)) {
            throw new MultithreadedFileDownloaderException("Invalid number of threads");
        }
        this.numberOfThreads = numberOfThreads;
    }

    private boolean isValidNumberOfThreads(int numberOfThreads) {
        return numberOfThreads > 0 && numberOfThreads <= Runtime.getRuntime().availableProcessors();
    }

    public void downloadFilesFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new MultithreadedFileDownloaderException("File not found");
        }
        Map<Integer, URL> urls = getUrlsFromFile(file);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (Map.Entry<Integer, URL> entry : urls.entrySet()) {
            int fileNumber = entry.getKey();
            URL fileUrl = entry.getValue();
            String fileExtension = "." + getFileExtensionFromUrl(fileUrl.toString());
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": Started downloading file " + fileNumber);
                    downloadFile(fileUrl, "./Day03/src/ex03/file_" + fileNumber + fileExtension);
                    System.out.println(Thread.currentThread().getName() + ": Finished downloading file " + fileNumber);
                } catch (IOException e) {
                    System.err.println("Error downloading file " + fileNumber);
                }
            });
        }

        executorService.shutdown();

    }

    private Map<Integer, URL> getUrlsFromFile(File fileName) {
        Map<Integer, URL> urls = new ConcurrentHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int i = Integer.parseInt(parts[0]);
                urls.put(i, new URL(parts[1]));
            }
        } catch (NumberFormatException | IOException e) {
            throw new MultithreadedFileDownloaderException("File read error");
        }
        return urls;
    }

    private void downloadFile(URL url, String destinationPath) throws IOException {
        try (InputStream in = url.openStream()) {
            Path destination = Path.of(destinationPath);
            Files.copy(in, destination);
        }
    }

    public String getFileExtensionFromUrl(String url) {
        String extension = "";

        int dotIndex = url.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = url.substring(dotIndex + 1);
        }

        return extension;
    }
}
