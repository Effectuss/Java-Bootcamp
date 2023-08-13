package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.InputArgs;

public class Program {
    public static void main(String[] args) {
        try {
            InputArgs inputArgs = new InputArgs();
            JCommander jCommander = new JCommander(inputArgs);
            jCommander.parse(args);
            System.out.println(inputArgs.getBlack() + inputArgs.getWhite());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
