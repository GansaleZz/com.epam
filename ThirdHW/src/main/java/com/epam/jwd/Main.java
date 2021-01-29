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
//        Properties figure = Properties.getInstance(masS[0].getType(),masS[0].getSquare());
//        figure.Property();
        PropertiesEn temp = PropertiesEn.INSTANCE;
        temp.setMasP(masS[0].getSquare());
        temp.setType(masS[0].getType());
        temp.Property();
    }

    public static void InitP(Point[] masP){
        for(int i=0;i< masP.length;++i){
            masP[i] = new Point();
        }
    }

    public static void InitL(Line[] masL){
        Point[] masP = new Point[2];
        masP[0] = new Point(0,1);
        masP[1] = new Point(10,14);
        masL[0] = new Line(masP);
        for(int i=1;i< masL.length;++i){
            masL[i] = new Line();
        }
    }

    public static void InitT(Triangle[] masT){
        for(int i=0;i< masT.length;++i){
            masT[i] = new Triangle();
        }
    }

    public static void InitS(Square[] masS){
        Point[] masP = new Point[4];
        masP[0] = new Point(3,2);
        masP[1] = new Point(4,3);
        masP[2] = new Point(5,2);
        masP[3] = new Point(4,1);
        for(int i=0;i< masS.length;++i){
            masS[i] = new Square(masP);
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
        }
    }

    public static void InfoAboutmasS(Square[] masS){
        for(int i=0;i<masS.length;++i){
            if(!masS[i].checkSamePoints())
                masS[i].existance();
        }
    }
}
