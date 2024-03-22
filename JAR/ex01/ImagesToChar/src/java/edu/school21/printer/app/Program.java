package edu.school21.printer.app;

import edu.school21.printer.logic.ConsoleImagePrinter;

import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        try {
            checkProgramArgument(args);
            ConsoleImagePrinter imagePrinter = new ConsoleImagePrinter(new File("/resources/it.bmp"),
                    args[0].charAt(0), args[1].charAt(0));
            imagePrinter.print();
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void checkProgramArgument(String[] inputArgs) throws IllegalArgumentException {
        if (inputArgs.length != 2) {
            throw new IllegalArgumentException("Input arguments count must be 2");
        }
        if (inputArgs[0].length() != 1 || inputArgs[1].length() != 1) {
            throw new IllegalArgumentException("The first and second arguments must be single characters");
        }
    }

}
