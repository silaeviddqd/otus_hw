package ru.otus;

import com.google.common.base.Strings;

public class HelloOtus {

    private static final String HELLO_OTUS = "Hello Otus!\n";
    private static final int REPEATS = 10;

    private static String greetingsOtus() {
        return HELLO_OTUS;
    }

    public static void main(String[] args) {
        System.out.println(Strings.repeat(greetingsOtus(), REPEATS));
    }
}
