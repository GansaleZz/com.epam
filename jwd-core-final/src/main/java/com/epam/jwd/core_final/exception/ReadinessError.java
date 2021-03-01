package com.epam.jwd.core_final.exception;

public class ReadinessError extends Exception{
    String name;

    public ReadinessError(String name){
        super();
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Crew member or spaceship with this name (" + name +") does not ready for next mission or already on mission! Try next time.";
    }
}
