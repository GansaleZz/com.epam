package com.epam.jwd.impl;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.*;
import com.epam.jwd.service.*;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAppContext implements ApplicationContext {

    @Override
    public Figure createFigureFactory(Point[] masP, Figure.FigureType type) throws FigureNotExistException {
        return new FigureFactory().getFigure(type,masP);
    }

    @Override
    public Figure createFigureFactory(Figure.FigureType type) throws FigureNotExistException{
        return new FigureFactory().getFigure(type);
    }

}
