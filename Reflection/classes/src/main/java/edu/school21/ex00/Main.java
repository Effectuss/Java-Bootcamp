package edu.school21.ex00;

import edu.school21.ex00.reflection.ReflectionClassHelper;
import edu.school21.ex00.reflection.ReflectionFieldHelper;
import edu.school21.ex00.reflection.ReflectionMethodHelper;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String LINE_SEPARATOR = "-------------------------";

    private static final String CLASSES_STRING = "Classes:";

    private static final String ENTER_CLASS_NAME = "Enter class name:";

    private static final String ERROR_CLASS_DOES_NOT_EXIST = "Class '%s.class' does not exist!";

    private static final String FIELDS = "Fields:\n";

    private static final String METHODS = "Methods:\n";

    private static final String PROMP_MSG_CREATE_OBJ = "Let's create an object.";

    private static final String PROMP_MSG_CHANGE_FIELD = "Enter name of the field for changing:";

    private static final String PROMP_MSG_VALUE = "Enter %s value:";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out, true, StandardCharsets.UTF_8);

        try (scanner; printWriter) {
            var classes = ReflectionClassHelper.getAllClassesFromPackage("edu.school21.ex00.classes");
            var classSimpleNames = ReflectionClassHelper.getClassSimpleNameSet(classes);
            printWriter.println(CLASSES_STRING);
            classSimpleNames.forEach(printWriter::println);
            printWriter.println(LINE_SEPARATOR + "\n" + ENTER_CLASS_NAME);

            String classSimpleName = scanner.nextLine();

            if (!classSimpleNames.contains(classSimpleName)) {
                throw new IllegalArgumentException(String.format(ERROR_CLASS_DOES_NOT_EXIST, classSimpleName));
            }

            Optional<Class<?>> clazz = ReflectionClassHelper.findClassBySimpleName(classes, classSimpleName);

            var allDeclaredClassFields = ReflectionFieldHelper.getAllDeclaredField(clazz.orElseThrow());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(LINE_SEPARATOR).append("\n").append(FIELDS);

            for (Field field : allDeclaredClassFields) {
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

            Constructor<?> constructor = clazz.orElseThrow().getConstructor(
                    ReflectionFieldHelper.getParametersType(allDeclaredClassFields)
            );

            List<Object> objects = new ArrayList<>();

            for (Field field : allDeclaredClassFields) {
                printWriter.println(field.getName());
                objects.add(readObjectFromCmd(scanner));
            }

            Object instance = constructor.newInstance(objects.toArray());

            printWriter.println("Object created: " + instance + "\n" + LINE_SEPARATOR + "\n" + PROMP_MSG_CHANGE_FIELD);
            scanner.nextLine();

            String fieldName = scanner.nextLine();

            var selectedField = instance.getClass().getDeclaredField(fieldName);
            printWriter.printf(PROMP_MSG_VALUE + "\n", selectedField.getType().getSimpleName());

            ReflectionFieldHelper.changePrivateField(selectedField, instance, readObjectFromCmd(scanner));

            printWriter.println("Object updated: " + instance + "\n" + LINE_SEPARATOR);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static Object readObjectFromCmd(Scanner scanner) {
        Object obj;

        if (scanner.hasNextBoolean()) {
            obj = scanner.nextBoolean();
        } else if (scanner.hasNextInt()) {
            obj = scanner.nextInt();
        } else if (scanner.hasNextDouble()) {
            obj = scanner.nextDouble();
        } else if (scanner.hasNextLong()) {
            obj = scanner.nextLong();
        } else {
            obj = scanner.nextLine();
        }

        return obj;
    }
}
