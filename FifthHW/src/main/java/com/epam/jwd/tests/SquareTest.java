package com.epam.jwd.tests;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.ConcreteAppContext;
import com.epam.jwd.model.Figure;
import com.epam.jwd.model.Point;
import com.epam.jwd.model.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {

    @Test
    void setSquare() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Point[] temp = new Point[4];
        temp[0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[2] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[3] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        Square S  = (Square) app.createFigureFactory(Figure.FigureType.SQUARE);
        S.setSquare(temp);
        assertEquals(temp,S.getSquare());
    }

    @Test
    void getSquare() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Point[] temp = new Point[4];
        temp[0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[2] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        temp[3] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
        Square S  = (Square) app.createFigureFactory(temp,Figure.FigureType.SQUARE);
        assertEquals(temp,S.getSquare());
    }

    @Test
    void getType() throws FigureNotExistException {
        ConcreteAppContext app = new ConcreteAppContext();
        Square S  = (Square) app.createFigureFactory(Figure.FigureType.SQUARE);
        assertEquals(Figure.FigureType.SQUARE,S.getType());
    }
}