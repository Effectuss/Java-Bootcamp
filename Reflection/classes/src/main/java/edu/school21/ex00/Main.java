package edu.school21.ex00;

import edu.school21.ex00.reflection.ReflectionManager;

public class Main {
    public static void main(String[] args) {
        var classes = ReflectionManager.getAllClassesFromPackage("edu.school21.ex00.classes");

        for(Class<?> clazz : classes) {
            System.out.println(clazz.getSimpleName());
        }
    }
}
