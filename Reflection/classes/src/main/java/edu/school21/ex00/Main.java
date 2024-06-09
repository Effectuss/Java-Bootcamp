package edu.school21.ex00;

import edu.school21.ex00.reflection.ReflectionClassHelper;
import edu.school21.ex00.reflection.ReflectionFieldHelper;
import edu.school21.ex00.reflection.ReflectionMethodHelper;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String LINE_SEPARATOR = "-------------------------";

    private static final String CLASSES_STRING = "Classes:";

    private static final String ENTER_CLASS_NAME = "Enter class name:";

    private static final String CLASS_DOES_NOT_EXIST = "Class '%s.class' does not exist!";

    private static final String FIELDS = "Fields:\n";

    private static final String METHODS = "Methods:\n";

    private static final String PROMP_MSG_CREATE_OBJ = "Let's create an object.";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

        try (scanner; printWriter) {
            var classes = ReflectionClassHelper.getAllClassesFromPackage("edu.school21.ex00.classes");
            var classSimpleNames = ReflectionClassHelper.getClassSimpleNameSet(classes);
            printWriter.println(CLASSES_STRING);
            classSimpleNames.forEach(printWriter::println);
            printWriter.println(LINE_SEPARATOR + "\n" + ENTER_CLASS_NAME);

            String inputClassName = scanner.nextLine();

            if (!classSimpleNames.contains(inputClassName)) {
                throw new IllegalArgumentException(String.format(CLASS_DOES_NOT_EXIST, inputClassName));
            }

            Optional<Class<?>> clazz = ReflectionClassHelper.findClassBySimpleName(classes, inputClassName);

            var allDeclaredClassField = ReflectionFieldHelper.getAllDeclaredField(clazz.orElseThrow());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(LINE_SEPARATOR).append("\n").append(FIELDS);

            for (Field field : allDeclaredClassField) {
                stringBuilder.append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
            }

            var allPublicMethod = ReflectionMethodHelper.getPublicMethodsExcludingToString(clazz.orElseThrow());

            stringBuilder.append("\n").append(METHODS);

            for (Method method : allPublicMethod) {
                stringBuilder.append(method.getReturnType().getSimpleName())
                        .append(" ")
                        .append(method.getName())
                        .append(Arrays.toString(method.getParameterTypes()))
                        .append(";\n");
            }

            stringBuilder.append(LINE_SEPARATOR).append("\n").append(PROMP_MSG_CREATE_OBJ);

            printWriter.println(stringBuilder);


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
