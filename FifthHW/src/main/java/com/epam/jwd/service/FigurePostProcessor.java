package com.epam.jwd.service;

import com.epam.jwd.exception.FigureException;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.FigureFactory;

public interface FigurePostProcessor {
    void figurepostprocess(boolean bool, Figure.FigureType type,Figure figure) throws FigureException;
}
