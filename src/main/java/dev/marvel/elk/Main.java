package dev.marvel.elk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.UUID;

public class Main {

    private static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.setProperty("app", "dev-marvel");
        System.setProperty("version", "2.23.1");
        System.setProperty("hostname", "localhost");

        for (int i = 0; i < 5_000; i++) {
            new Thread(() -> {
                ThreadContext.put("UUID", UUID.randomUUID().toString());
                log.info("Some clever log message");
            }).start();
        }
    }
}
