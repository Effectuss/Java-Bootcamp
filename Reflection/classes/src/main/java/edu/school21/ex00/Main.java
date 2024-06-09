package edu.school21.ex00;

import edu.school21.ex00.reflection.ClassWorkerUtil;
import edu.school21.ex00.reflection.ReflectionManager;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static final String LINE_SEPARATOR = "-".repeat(25);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                var classes = ReflectionManager.getAllClassesFromPackage("edu.school21.ex00.classes");
                var classesSimpleNames = ClassWorkerUtil.getClassSimpleNameSet(classes);
                System.out.println("Classes: ");
                System.out.println(LINE_SEPARATOR);
                ClassWorkerUtil.printClassSimpleName(new PrintWriter(System.out, true), classesSimpleNames);
                break;
            }
        }
    }
}
