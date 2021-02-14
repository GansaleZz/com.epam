package com.epam.jwd.tests;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.ConcreteAppContext;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.Line;
import com.epam.jwd.model.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureFactoryTest {

    @Test
    void getFigure() throws FigureNotExistException {
        try {
            ConcreteAppContext app = new ConcreteAppContext();
            Point[] temp = new Point[3];
            temp[0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            temp[1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            temp[2] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            Line l = (Line) app.createFigureFactory(temp, Figure.FigureType.LINE);
        }catch (FigureNotExistException e){
            System.out.println("exc");
        }
    }
}