package edu.school21.ex00.reflection;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class ClassWorkerUtil {

    private ClassWorkerUtil() {
    }

    public static List<String> getClassSimpleNameList(Set<Class<?>> classes) {
        return classes.stream().map(Class::getSimpleName).toList();
    }

    public static Set<String> getClassSimpleNameSet(Set<Class<?>> classes) {
        return classes.stream().map(Class::getSimpleName).collect(Collectors.toSet());
    }

    public static void printClassSimpleName(PrintWriter printWriter, Set<String> classes) {
        printWriter.println(Arrays.toString(classes.toArray()));
    }
}
