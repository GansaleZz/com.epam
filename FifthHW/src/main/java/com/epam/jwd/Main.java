package com.epam.jwd;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.ConcreteAppContext;
import com.epam.jwd.model.*;
import com.epam.jwd.strategy.*;


public class Main {
    public static void main(String[] args) throws FigureNotExistException {
        Point[] masP = new Point[4];
        Line[] masL = new Line[2];
        Triangle[] masT = new Triangle[2];
        Square[] masS = new Square[1];
        ConcreteAppContext app = new ConcreteAppContext();
        InitP(masP,app);
        InitL(masL,app);
        InitT(masT,app);
        InitS(masS,app);
        MultiAngleFigure Pen = InitPen(app);
        MultiAngleFigure Hex = InitHex(app);
        InfoAboutmasP(masP);
        InfoAboutmasL(masL);
        InfoAboutmasT(masT);
        InfoAboutmasS(masS);
        InfoAboutPen(Pen);
        InfoAboutHex(Hex);

    }


    public static void InitP(Point[] masP,ConcreteAppContext app) throws FigureNotExistException {
        for(int i=0;i< masP.length;++i){
            masP[i] =(Point) app.createFigureFactory(Figure.FigureType.POINT);
        }
    }

    public static void InitL(Line[] masL,ConcreteAppContext app) throws FigureNotExistException {
        for(int i=0;i< masL.length;++i){
            Point[][] temp = new Point[2][2];
            temp[i][0] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            temp[i][1] = (Point) app.createFigureFactory(Figure.FigureType.POINT);
            masL[i] = (Line) app.createFigureFactory(temp[i],Figure.FigureType.LINE);
        }
    }

    public static void InitT(Triangle[] masT,ConcreteAppContext app) throws FigureNotExistException {
        for(int i=0;i< masT.length;++i){
            masT[i] = (Triangle) app.createFigureFactory(Figure.FigureType.TRIANGLE);
        }
    }

    public static void InitS(Square[] masS,ConcreteAppContext app) throws FigureNotExistException {
        for(int i=0;i< masS.length;++i){
            masS[i] = (Square) app.createFigureFactory(Figure.FigureType.SQUARE);
        }
    }

    public static MultiAngleFigure InitPen(ConcreteAppContext app) throws FigureNotExistException {
        return (MultiAngleFigure) app.createFigureFactory(Figure.FigureType.PENTAGON);
    }

    public static MultiAngleFigure InitHex(ConcreteAppContext app) throws FigureNotExistException {
        return (MultiAngleFigure) app.createFigureFactory(Figure.FigureType.HEXAGON);
    }

    public static void InfoAboutmasP(Point[] masP){
        int i = 0;
        do{
            System.out.println(masP[i].toString());
            ++i;
        }while(i<masP.length);
    }

    public static void InfoAboutmasL(Line[] masL){
        for (Line line : masL) {
            if (!line.checkSamePoints())
                System.out.println(line.toString());
        }
    }

    public static void InfoAboutmasT(Triangle[] masT) throws FigureNotExistException {
        for(int i=0;i<2;++i){
            if(!masT[i].checkSamePoints()) {
                masT[i].existance();
                if(masT[i].checkExistance()) {
                    Properties figure =  Properties.getInstance(masT[i].getType(), masT[i]);
                    figure.Property();
                }
            }
        }
    }

    public static void InfoAboutmasS(Square[] masS) throws FigureNotExistException {
        for (Square s : masS) {
            if (!s.checkSamePoints()) {
                s.existance();
                if (s.checkExistance()) {
                    Properties figure = Properties.getInstance(s.getType(), s);
                    figure.Property();
                }
            }
        }
    }

    public static void InfoAboutPen(MultiAngleFigure Pen) throws FigureNotExistException {
        if(!Pen.checkSamePoints()){
            Pen.existance();
            if(Pen.checkExistance()){
                Properties figure = Properties.getInstance(Pen.getType(),Pen);
                figure.Property();
            }
        }
    }

    public static void InfoAboutHex(MultiAngleFigure Hex) throws FigureNotExistException {
        if(!Hex.checkSamePoints()){
            Hex.existance();
            if(Hex.checkExistance()){
                Properties figure = Properties.getInstance(Hex.getType(),Hex);
                figure.Property();
            }
        }
    }
}
