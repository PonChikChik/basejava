package com.ponchikchik.webapp;

import com.ponchikchik.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("Reflection");
        Class<? extends Resume> resumeClass = resume.getClass();

        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        // TODO : invoke r.toString via reflection

        try {
            Method method = resumeClass.getMethod("toString");
            Object result = method.invoke(resume);
            System.out.println(result);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(resume);
    }
}
