package edu.school21.ex00.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class ReflectionMethodHelper {

    private ReflectionMethodHelper() {
    }

    public static Set<Method> getPublicMethodsExcludingToString(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .filter(method -> !method.getName().equals("toString"))
                .collect(Collectors.toSet());
    }
}
