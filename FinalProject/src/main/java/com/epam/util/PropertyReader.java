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

    public static Properties getProperties() throws FileException {
        if(instance == null){
            loadProperties();
            instance = new PropertyReader();
        }
        return properties;
    }

    public static void loadProperties() throws FileException {
        final String propertiesFileName = "/Users/andrew_wannasesh/Folders/EpamJAva/FinalProject/src/main/resources/application.properties";
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(propertiesFileName);
            properties.load(inputStream);
            logger.info("Info from "+propertiesFileName+" were completely read");
        }catch (IOException e){
            logger.error(e.getMessage());
            throw new FileException(e);
        } finally {
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            }catch(IOException e){
                logger.error(e.getMessage());
                throw new FileException(e);
            }
        }
    }
}
