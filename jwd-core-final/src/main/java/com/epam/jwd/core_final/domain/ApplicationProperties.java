package com.epam.jwd.core_final.domain;

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
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final int fileRefreshRate;
    private final String dateTimeFormat;

    public ApplicationProperties(String inputRootDir, String outputRootDir, String crewFileName, String missionsFileName, String spaceshipsFileName, int fileRefreshRate, String dateTimeFormat) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFileName;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = dateTimeFormat;
    }

    
    public int getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "inputRootDir='" + inputRootDir + '\'' +
                ", outputRootDir='" + outputRootDir + '\'' +
                ", crewFileName='" + crewFileName + '\'' +
                ", missionsFileName='" + missionsFileName + '\'' +
                ", spaceshipsFileName='" + spaceshipsFileName + '\'' +
                ", fileRefreshRate=" + fileRefreshRate +
                ", dateTimeFormat='" + dateTimeFormat + '\'' +
                '}';
    }
}
