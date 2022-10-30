package ru.wbest.otus;

import lombok.extern.java.Log;
import ru.wbest.otus.annotations.After;
import ru.wbest.otus.annotations.Before;
import ru.wbest.otus.annotations.Test;

@Log
public class OtusTest {
    private String setUpHello;
    private String nullPointerString = null;

    @Before
    public void setUp() {
        // Before
        log.info("Hello before!");

        setUpHello = "Hello Otus!";

        log.info(setUpHello);
    }

    @Test
    public void otusTest() {
        // Test
        log.info("Hello test!");
    }

    @Test
    public void otusTest2() {
        // Test
        log.info("Hello test2!");
    }

    @Test
    public void otusTest3() {
        // Test
        log.info("Hello test3!");
    }

    @Test
    public void otusNullPointerTest() {
        // Test
        log.info("Hello null pointer test!");

        // Accessing NPE
        nullPointerString.length();
    }

    @Test
    public void otusTestAfterNullPointerException() {
        // Test
        log.info("Hello test after exception!");
    }

    @After
    public void after() {
        // After
        log.info("Hello after");
    }
}
