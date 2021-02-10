package com.epam.jwd.impl;

import com.epam.jwd.service.FigurePreProcessor;
import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class FigureExistencePreProcessor implements FigurePreProcessor {
    private static final Logger log = Logger.getLogger(Line.class);

    @Override
    public void figurePreProcess(Point[] masP, Figure.FigureType type ){
        switch(type) {
            case POINT: {
                if (masP.length != 1) {
                    log.error("Length of input mas of points should be equals to 1! ");
                }
                break;
            }
            case LINE:{
                if (masP.length != 2) {
                    log.error("Length of input mas of points should be equals to 2! ");
                }
                break;
            }
            case TRIANGLE:{
                if (masP.length != 3) {
                    log.error("Length of input mas of points should be equals to 3! ");
                }
                break;
            }
            case SQUARE:{
                if (masP.length != 4) {
                    log.error("Length of input mas of points should be equals to 4! ");
                }
                break;
            }
            case PENTAGON:{
                if (masP.length != 5) {
                    log.error("Length of input mas of points should be equals to 5! ");
                }
                break;
            }
            case HEXAGON:{
                if (masP.length != 6) {
                    log.error("Length of input mas of points should be equals to 6! ");
                }
                break;
            }
        }
    }
}
