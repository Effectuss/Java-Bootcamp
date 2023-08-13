package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.InputArgs;

public class Program {
    public static void main(String[] args) {
        try {
            InputArgs inputArgs = new InputArgs();
            JCommander.newBuilder()
                    .addObject(inputArgs)
                    .build()
                    .parse(args);


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
