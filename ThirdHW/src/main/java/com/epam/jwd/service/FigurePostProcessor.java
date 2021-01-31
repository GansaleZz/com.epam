package com.epam.jwd.service;

import com.epam.jwd.exception.FigureException;
import com.epam.jwd.model.Figure;

public interface FigurePostProcessor {
    void Figureprocess(boolean bool, Figure.FigureType type) throws FigureException;
}
