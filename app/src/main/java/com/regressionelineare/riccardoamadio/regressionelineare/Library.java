package com.regressionelineare.riccardoamadio.regressionelineare;

/**
 * Created by riccardoamadio on 04/04/17.
 */

import com.regressionelineare.riccardoamadio.regressionelineare.MyException;

public class Library {

    private double coefficente_angolare=0.00;

    private double termine_noto=0.00;

    private double[] x;

    private double[] y;

    public Library(double[] _x,double[] _y){

        this.x =_x;

        this.y =_y;
    }


    public double coefficente_angolare() throws MyException {
        if(this.x==null || this.y==null)
            throw new MyException("Metodo:coefficente_angolare \n Motivo: dati non inseriti");
        if(this.x.length!=this.y.length)
            throw new MyException("Metodo:coefficente_angolare \n Motivo: i due vettori hanno lunghezze diverse");

        this.coefficente_angolare=covergenza()/varianza();
        return this.coefficente_angolare;
    }

    public double termine_noto() throws MyException {
        if(this.x==null || this.y==null)
            throw new MyException("Metodo:coefficente_angolare \n Motivo: dati non inseriti");
        if(this.x.length!=this.y.length)
            throw new MyException("Metodo:coefficente_angolare \n Motivo: i due vettori hanno lunghezze diverse");
        if(this.coefficente_angolare==Double.NaN)
            this.coefficente_angolare();

        double avg_y=this.calcAvgY();
        double avg_x=this.calcAvgX();
        this.termine_noto=avg_y-(avg_x*this.coefficente_angolare);
        System.out.println("termine noto:"+(avg_y-(avg_x*this.coefficente_angolare)));
        return this.termine_noto;
    }

    private double calcAvg(double[] array) throws  MyException{
        if(array.length!=0){
            double avg=0;
            for(int i=0;i<array.length;i++)            avg+=array[i];
            avg =avg/array.length;

            return avg;
        }else
            throw new MyException("Metodo: CALCAVG \n Motivo: Array Vuoto");

    }


    private double calcAvgX() throws MyException {
        try {
            return this.calcAvg(this.x);
        } catch (MyException e) {
           throw new MyException("Metodo: CALCAVGX \n Motivo: calcavg non ha funzionato");

        }
    }

    private double calcAvgY() throws MyException {
        try {
            return this.calcAvg(this.y);
        } catch (MyException e) {
            throw new MyException("Metodo: CALCAVGY \n Motivo: calcavg non ha funzionato");

        }
    }

    private double covergenza() throws MyException {
        double avg_x,avg_y=0;
        try {
             avg_x=this.calcAvgX();
        } catch (MyException e) {
            throw new MyException("Metodo: covergenza \n Motivo: calcAvgX non ha funzionato");
        }
        try {
             avg_y=this.calcAvgY();
        } catch (MyException e) {
            throw new MyException("Metodo: covergenza \n Motivo: calcAvgY non ha funzionato");
        }
        double covergenza=0;
        for(int i=0;i<this.x.length;i++){
            covergenza+=((this.x[i]-avg_x)*(this.y[i]-avg_y));
        }
        return covergenza;
    }


    private double varianza() throws MyException {
        double avg_x,varianza=0;
        try {
            avg_x=this.calcAvgX();
        } catch (MyException e) {
            throw new MyException("Metodo: varianza \n Motivo: calcAvgX non ha funzionato");
        }
        for(int i=0;i<this.x.length;i++){
            varianza+=(this.x[i]-avg_x)*(this.x[i]-avg_x);
        }
        return varianza;

    }

}
