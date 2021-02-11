package com.epam.jwd.impl;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.*;
import com.epam.jwd.service.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAppContext implements ApplicationContext {

    private List<Figure> listF = new ArrayList<>();

    public List<Figure> getListF(){
        return this.listF;
    }

    public void setListF(List<Figure> listF){
        this.listF = listF;
    }

    @Override
    public Figure createFigureFactory(Point[] masP, Figure.FigureType type) throws FigureNotExistException {
        Figure figure = null;
        boolean bool = false;
        int i;
        if(this.listF.size() != 0){
            for (i = 0; i < this.listF.size(); i++) {
                for(int j = 0; j < masP.length; j++) {
                    if(this.listF.get(i).getMasP().length != masP.length) break;
                    if(this.listF.get(i).getMasP()[j].getX() == masP[j].getX() && this.listF.get(i).getMasP()[j].getY() == masP[j].getY()){
                        figure = this.listF.get(i);
                        bool = true;
                    }else{
                        bool = false;
                        break;
                    }
                }
                if (bool) break;
            }
        }
        if(!bool || this.listF.size() == 0){
                this.listF.add(new FigureFactory().getFigure(type, masP));
                figure = this.listF.get(this.listF.size() - 1);
            }
        return figure;
    }

    @Override
    public Figure createFigureFactory(Figure.FigureType type) throws FigureNotExistException{
        boolean bool = false;
        int i;
        Figure figure = new FigureFactory().getFigure(type);
        if(this.listF.size() != 0){
            for (i = 0; i < this.listF.size(); i++) {
                for (int j = 0; j < figure.getMasP().length; j++) {
                    if(this.listF.get(i).getMasP().length != figure.getMasP().length) break;
                    if(this.listF.get(i).getMasP()[j].getX() == figure.getMasP()[j].getX() && this.listF.get(i).getMasP()[j].getY() == figure.getMasP()[j].getY()){
                        bool = true;
                    }else{
                        bool = false;
                        break;
                    }
                }
                if (bool) break;
            }
        }
        if(!bool || this.listF.size() == 0){
            this.listF.add(figure);
        }
        return figure;
    }

}
