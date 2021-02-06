package com.epam.jwd.service;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.FigureFactory;
import com.epam.jwd.model.Point;

public interface ApplicationContext {
    Figure createFigureFactory(Point[] masP, Figure.FigureType type) throws FigureNotExistException;
    Figure createFigureFactory(Figure.FigureType type) throws FigureNotExistException;
}
