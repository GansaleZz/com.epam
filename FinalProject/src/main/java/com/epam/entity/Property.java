package com.epam.entity;

import com.epam.util.PropertyReader;

import java.util.Properties;


/**
 * Class which keeps information for working with db from application.properties
 *
 * @see PropertyReader
 * @author Andrey Rubin
 */
public final class Property {
    private final String URL;
    private final String SCHEME;
    private final String PASSWORD;
    private final String USER;
    private final int INIT_POOL_SIZE;
    private final int MAX_POOL_SIZE;
    private final String MAIL;
    private final String MAIL_PASS;
    private static Property instance = null;

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

    public int getINIT_POOL_SIZE() {
        return INIT_POOL_SIZE;
    }

    public int getMAX_POOL_SIZE() {
        return MAX_POOL_SIZE;
    }

    public String getMAIL_PASS() {
        return MAIL_PASS;
    }

    public String getMAIL() {
        return MAIL;
    }

    @Override
    public String toString() {
        return "Property{" +
                "URL='" + URL + '\'' +
                ", SCHEME='" + SCHEME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", USER='" + USER + '\'' +
                ", INIT_POOL_SIZE=" + INIT_POOL_SIZE +
                ", MAX_POOL_SIZE=" + MAX_POOL_SIZE +
                ", MAIL='" + MAIL + '\'' +
                ", MAIL_PASS='" + MAIL_PASS + '\'' +
                '}';
    }

    private Property(){
        Properties properties = PropertyReader.getProperties();
        URL = properties.getProperty("db.url");
        SCHEME =  properties.getProperty("db.scheme");
        PASSWORD = properties.getProperty("db.password");
        USER = properties.getProperty("db.user");
        INIT_POOL_SIZE = Integer.parseInt(properties.getProperty("db.init.pool.size"));
        MAX_POOL_SIZE = Integer.parseInt(properties.getProperty("db.max.pool.size"));
        MAIL = properties.getProperty("mail");
        MAIL_PASS = properties.getProperty("mail.pass");
    }
}
