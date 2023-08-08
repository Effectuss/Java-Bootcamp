package ex01;

import java.io.File;

public class Program {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments!");
            }
            DictionaryFileCreator.createDictionaryFile(new File(args[0]), new File(args[1]), "Dictionary.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
