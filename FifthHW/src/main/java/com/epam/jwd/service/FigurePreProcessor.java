package com.epam.jwd.service;

import com.epam.jwd.exception.FigureException;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.*;

public interface FigurePreProcessor {
    void figurePreProcess(Point[] masP, Figure.FigureType type) throws FigureException;
}
