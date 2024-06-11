package edu.school21.ex00;

import edu.school21.ex00.classes.Car;
import edu.school21.ex00.classes.User;
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
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String LINE_SEPARATOR = "-------------------------";

    private static final String CLASSES_STRING = "Classes:";

    private static final String ENTER_CLASS_NAME = "Enter class name:";

    private static final String ERROR_CLASS_DOES_NOT_EXIST = "Class '%s.class' does not exist!";

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

            String inputLine = scanner.nextLine();

            if (!classSimpleNames.contains(inputLine)) {
                throw new IllegalArgumentException(String.format(ERROR_CLASS_DOES_NOT_EXIST, inputLine));
            }

            Optional<Class<?>> clazz = ReflectionClassHelper.findClassBySimpleName(classes, inputLine);

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

                objects.add(obj);
            }

            Object instance = constructor.newInstance(objects.toArray());

            System.out.println("Object created: " + instance);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
