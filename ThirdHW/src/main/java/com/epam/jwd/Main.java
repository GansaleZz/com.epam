package com.epam.jwd;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.*;
import com.epam.jwd.strategy.*;


public class Main {
    public static void main(String[] args) throws FigureNotExistException {
        Point[] masP = new Point[4];
        Line[] masL = new Line[2];
        Triangle[] masT = new Triangle[2];
        Square[] masS = new Square[1];
        InitP(masP);
        InitL(masL);
        InitT(masT);
        InitS(masS);
        MultiAngleFigure Pen = InitPen();
        MultiAngleFigure Hex = InitHex();
        InfoAboutmasP(masP);
        InfoAboutmasL(masL);
        InfoAboutmasT(masT);
        InfoAboutmasS(masS);
        InfoAboutPen(Pen);
        InfoAboutHex(Hex);
    }

    public static void InitP(Point[] masP) throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masP.length;++i){
            masP[i] = (Point) factory.getFigure(Figure.FigureType.POINT);
        }
    }

    public static void InitL(Line[] masL) throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masL.length;++i){
            masL[i] = (Line) factory.getFigure(Figure.FigureType.LINE);
        }
    }

    public static void InitT(Triangle[] masT) throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masT.length;++i){
            masT[i] = (Triangle) factory.getFigure(Figure.FigureType.TRIANGLE);
        }
    }

    public static void InitS(Square[] masS) throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masS.length;++i){
            masS[i] = (Square) factory.getFigure(Figure.FigureType.SQUARE);
        }
    }

    public static MultiAngleFigure InitPen() throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        MultiAngleFigure Pen = (MultiAngleFigure) factory.getFigure(Figure.FigureType.PENTAGON);
        return Pen;
    }

    public static MultiAngleFigure InitHex() throws FigureNotExistException {
        FigureFactory factory = new FigureFactory();
        MultiAngleFigure Hex = (MultiAngleFigure) factory.getFigure(Figure.FigureType.HEXAGON);
        return Hex;
    }

    public static void InfoAboutmasP(Point[] masP){
        int i = 0;
        do{
            System.out.println(masP[i].toString());
            ++i;
        }while(i<masP.length);
    }

    public static void InfoAboutmasL(Line[] masL){
        for(int i=0;i<masL.length;++i){
            if(!masL[i].checkSamePoints())
                System.out.println(masL[i].toString());
        }
    }

    public static void InfoAboutmasT(Triangle[] masT) throws FigureNotExistException {
        for(int i=0;i<2;++i){
            if(!masT[i].checkSamePoints()) {
                masT[i].existance();
                if(masT[i].checkExistance()) {
                    Properties figure =  Properties.getInstance(masT[i].getType(), masT[i].getTriangle());
                    figure.Property();
                }
            }
        }
    }

    public static void InfoAboutmasS(Square[] masS) throws FigureNotExistException {
        for(int i=0;i<masS.length;++i){
            if(!masS[i].checkSamePoints()) {
                masS[i].existance();
                if(masS[i].checkExistance()) {
                    Properties figure = Properties.getInstance(masS[i].getType(), masS[i].getSquare());
                    figure.Property();
                }
            }
        }
    }

    public static void InfoAboutPen(MultiAngleFigure Pen) throws FigureNotExistException {
        if(!Pen.checkSamePoints()){
            Pen.existance();
            if(Pen.checkExistance()){
                Properties figure = Properties.getInstance(Pen.getType(),Pen.getMultiAngleFigure());
                figure.Property();
            }
        }
    }

    public static void InfoAboutHex(MultiAngleFigure Hex) throws FigureNotExistException {
        if(!Hex.checkSamePoints()){
            Hex.existance();
            if(Hex.checkExistance()){
                Properties figure = Properties.getInstance(Hex.getType(),Hex.getMultiAngleFigure());
                figure.Property();
            }
        }
    }
}
