package com.epam.jwd.tests;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.ConcreteAppContext;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.Line;
import com.epam.jwd.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LineTest {


    @Test
    void getLine() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Point[] temp = new Point[2];
            temp[0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            temp[1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        Line l  = (Line) app.createFigureFactory(temp,Figure.FigureType.LINE);
       assertEquals(temp,l.getLine());
    }

    @Test
    void setLine() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Point[] temp = new Point[2];
        temp[0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        Line l  = (Line) app.createFigureFactory(Figure.FigureType.LINE);
        l.setLine(temp);
        assertEquals(temp,l.getLine());
    }

    @Test
    void getType() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Line l  = (Line) app.createFigureFactory(Figure.FigureType.LINE);
        assertEquals(Figure.FigureType.LINE,l.getType());
    }

}