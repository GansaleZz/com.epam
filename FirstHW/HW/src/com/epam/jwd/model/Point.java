package  com.epam.jwd.model;

import org.apache.log4j.Logger;

public class Point {
    private static final Logger log = Logger.getLogger(Point.class);

    public void FirstAction(){
        System.out.println("First action complete");
        log.info("Info message!");
        SecondAction();
    }

    private void SecondAction(){
        System.out.println("Second action complete");
        log.error("Error message!");
    }
}
