package ru.wbest.otus.runner;

import lombok.extern.java.Log;
import ru.wbest.otus.OtusTest;
import ru.wbest.otus.annotations.After;
import ru.wbest.otus.annotations.Before;
import ru.wbest.otus.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Log
public class TestRunner {

    private final Object instance;
    private final Method[] methods;

    public TestRunner(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        instance = clazz.getDeclaredConstructors()[0].newInstance(null);
        methods = clazz.getDeclaredMethods();
    }

    public static void main(String[] args) throws Exception {
        TestRunner testRunner = new TestRunner(OtusTest.class);

        testRunner.before();

        testRunner
                .getTests()
                .stream()
                .map(testRunner::test)
                .forEach(log::info);

        testRunner.after();

    }

    private void before() {
        Method before = Arrays
                .stream(methods)
                .filter(method -> method.isAnnotationPresent(Before.class))
                .findFirst()
                .orElseThrow();

        try {
            before.invoke(instance, null);
        } catch (Exception e) {
            // ignored
        }
    }

    private String test(Method method) {
        try {
            method.invoke(instance, null);
        } catch (Exception e) {
            return "Test " + method.getName() + " has errors, caused by: " + e.getCause().getMessage();
        }

        return "Test " + method.getName() + " successfully passed";
    }

    public void after() {
        Method before = Arrays
                .stream(methods)
                .filter(method -> method.isAnnotationPresent(After.class))
                .findFirst()
                .orElseThrow();

        try {
            before.invoke(instance, null);
        } catch (Exception e) {
            // ignored
        }
    }

    private List<Method> getTests() {
        return Arrays
                .stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .toList();
    }
}
