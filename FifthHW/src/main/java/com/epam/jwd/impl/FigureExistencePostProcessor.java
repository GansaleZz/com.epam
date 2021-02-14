package com.epam.jwd.impl;

import com.epam.jwd.model.Figure;
import com.epam.jwd.model.Line;
import com.epam.jwd.service.FigurePostProcessor;
import org.apache.log4j.Logger;

public class FigureExistencePostProcessor implements FigurePostProcessor {
    private static final Logger log = Logger.getLogger(Line.class);

    @Override
    public void figurepostprocess(boolean bool,Figure.FigureType type,Figure figure) {
        if(bool){
            log.info("Object "+type+" was created!");
        }else{
            log.error("Object "+type+" wasn't created");
        }
    }
}
