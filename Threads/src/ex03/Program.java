package ex03;

import ex03.filedownloader.MultithreadedFileDownloader;
import ex03.parser.ProgramArgumentsParser;
import ex03.parser.exception.ProgramArgumentsParserException;

public class Program {
    public static void main(String[] args) {
        try {
            int numberOfThreads = ProgramArgumentsParser.getNumberOfThreads(args);
            MultithreadedFileDownloader multithreadedFileDownloader =
                    new MultithreadedFileDownloader(numberOfThreads);
            multithreadedFileDownloader.downloadFilesFromFile("./Day03/src/ex03/file_urls.txt");
        } catch (ProgramArgumentsParserException e) {
            System.err.println(e.getMessage());
        }
    }
}