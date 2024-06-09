package edu.school21.ex00.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class ReflectionFieldHelper {

    private ReflectionFieldHelper() {
    }

    public static Set<Field> getAllDeclaredField(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toSet());
    }
}
