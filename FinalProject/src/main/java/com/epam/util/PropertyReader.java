package com.epam.util;

import com.epam.exceptions.FileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Properties properties = new Properties();
    private static PropertyReader instance = null;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PropertyReader.class);


    private PropertyReader() {
    }

    public static Properties getProperties(){
        if(instance == null){
            loadProperties();
            instance = new PropertyReader();
        }
        return properties;
    }

    public static void loadProperties() {
        final String propertiesFileName = "/Users/andrew_wannasesh/Folders/EpamJAva/FinalProject/src/main/resources/application.properties";
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(propertiesFileName);
            properties.load(inputStream);
            logger.info("Info from "+propertiesFileName+" were completely read");
        }catch (IOException e){ // <- need custom Exception
            logger.error(e.getMessage());
        } finally {
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch(IOException e){ // <- need custom Exception
                logger.error(e.getMessage());
            }
        }
    }
}
