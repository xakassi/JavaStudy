package netcracker.practice.reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Solution {
    private static void getMethods(Class clazz) {
        System.out.println(clazz.getName() + ":");
        Arrays.asList(clazz.getDeclaredMethods()).forEach(method -> {
            if (Modifier.isPublic(method.getModifiers())) {
                String methodToString = "\t";
                if (method.isAnnotationPresent(MyAnnotation.class)) {
                    methodToString += " * ";
                }
                methodToString += method.toString();
                System.out.println(methodToString);
            }
        });

        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            getMethods(superClass);
        }
    }

    public static void main(String[] args) {
        getMethods(Circle.class);
    }
}
