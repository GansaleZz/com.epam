package com.epam.entity;

import com.epam.util.PropertyReader;

import java.util.Properties;

public final class Property {
    private final String URL;
    private final String SCHEME;
    private final String PASSWORD;
    private final String USER;
    private final int INITPOOLSIZE;
    private final int MAXPOOLSIZE;
    private static Property instance = null;

    private Property(){
        Properties properties = PropertyReader.getProperties();
        URL = properties.getProperty("db.url");
        SCHEME =  properties.getProperty("db.scheme");
        PASSWORD = properties.getProperty("db.password");
        USER = properties.getProperty("db.user");
        INITPOOLSIZE = Integer.valueOf(properties.getProperty("db.initpoolsize"));
        MAXPOOLSIZE = Integer.valueOf(properties.getProperty("db.maxpoolsize"));
    }

    public static Property getInstance(){
        if(instance == null){
            instance = new Property();
        }
        return instance;
    }

    public String getURL() {
        return URL;
    }

    public String getSCHEME() {
        return SCHEME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getUSER() {
        return USER;
    }

    public int getINITPOOLSIZE() {
        return INITPOOLSIZE;
    }

    public int getMAXPOOLSIZE() {
        return MAXPOOLSIZE;
    }

    @Override
    public String toString() {
        return "Property{" +
                "URL='" + URL + '\'' +
                ", SCHEME='" + SCHEME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", USER='" + USER + '\'' +
                ", INITPOOLSIZE=" + INITPOOLSIZE +
                ", MAXPOOLSIZE=" + MAXPOOLSIZE +
                '}';
    }
}
