package common;


import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(Thread.currentThread().getName());
        log.info("hello world");
        log.warning("this is a warning message");
        System.out.println("log = " + log);

    }
}
