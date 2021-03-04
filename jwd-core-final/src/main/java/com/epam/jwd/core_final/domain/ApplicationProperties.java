package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */

//OK

public final class ApplicationProperties {
    private static Properties properties = PropertyReaderUtil.getProperties();
    private static final String inputRootDir = properties.getProperty("inputRootDir");
    private static final String outputRootDir = properties.getProperty("outputRootDir");;
    private static final String crewFileName  = properties.getProperty("crewFileName");
    private static final String missionsFileName = properties.getProperty("missionsFileName");
    private static final String spaceshipsFileName = properties.getProperty("spaceshipsFileName");
    private static final String spacemapFileName = properties.getProperty("spacemapFileName");
    private static final int fileRefreshRate = Integer.valueOf(properties.getProperty("fileRefreshRate"));
    private static final String dateTimeFormat = properties.getProperty("dateTimeFormat");

    private ApplicationProperties(){}


    public static int getFileRefreshRate() {
        return fileRefreshRate;
    }

    public static String getCrewFileName() {
        return crewFileName;
    }

    public static String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public static String getInputRootDir() {
        return inputRootDir;
    }

    public static String getMissionsFileName() {
        return missionsFileName;
    }

    public static String getOutputRootDir() {
        return outputRootDir;
    }

    public static String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public static String getSpacemapFileName() {
        return spacemapFileName;
    }

}
