package com.epam.jwd;

import com.epam.jwd.model.*;
import com.epam.jwd.strategy.*;
import org.apache.log4j.Logger;


public class Main {
    public static void main(String[] args) {
        Point[] masP = new Point[4];
        Line[] masL = new Line[2];
        Triangle[] masT = new Triangle[2];
        Square[] masS = new Square[1];
        InitP(masP);
        InitL(masL);
        InitT(masT);
        InitS(masS);
        InfoAboutmasP(masP);
        InfoAboutmasL(masL);
        InfoAboutmasT(masT);
        InfoAboutmasS(masS);
    }

    public static void InitP(Point[] masP){
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masP.length;++i){
            masP[i] = (Point) factory.getFigure(Figure.FigureType.POINT);
        }
    }

    public static void InitL(Line[] masL){
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masL.length;++i){
            masL[i] = (Line) factory.getFigure(Figure.FigureType.LINE);
        }
    }

    public static void InitT(Triangle[] masT){
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masT.length;++i){
            masT[i] = (Triangle) factory.getFigure(Figure.FigureType.TRIANGLE);
        }
    }

    public static void InitS(Square[] masS){
        FigureFactory factory = new FigureFactory();
        for(int i=0;i< masS.length;++i){
            masS[i] = (Square) factory.getFigure(Figure.FigureType.SQUARE);
        }
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

    public static void InfoAboutmasT(Triangle[] masT){
        for(int i=0;i<masT.length;++i){
            if(!masT[i].checkSamePoints())
                masT[i].existance();
                Properties figure = Properties.getInstance(masT[i].getType(),masT[i].getTriangle());
                figure.Property();
        }
    }

    public static void InfoAboutmasS(Square[] masS){
        for(int i=0;i<masS.length;++i){
            if(!masS[i].checkSamePoints())
                masS[i].existance();
                Properties figure = Properties.getInstance(masS[i].getType(),masS[i].getSquare());
                figure.Property();
        }
    }
}
