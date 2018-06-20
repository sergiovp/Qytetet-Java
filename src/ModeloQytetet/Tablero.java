/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloQytetet;

import java.util.ArrayList;

/**
 *
 * @author merino
 */
public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;
    
    public Tablero(){
        inicializar();
    }
    
    private void inicializar(){
        casillas = new ArrayList();
        //INICIALIZAR CARCEL
        casillas.add(new OtraCasilla(0, 0, TipoCasilla.SALIDA));
        casillas.add(new Calle(1, 100, new TituloPropiedad("UNO", 50, 10f, 150, 250)));
        casillas.add(new OtraCasilla(2, 200 ,TipoCasilla.IMPUESTO));
        casillas.add(new Calle(3, 100, new TituloPropiedad("TRES", 55, 11f, 235, 300)));
        casillas.add(new Calle(4, 100, new TituloPropiedad("CUATRO", 60, 12f, 320, 350)));
        carcel= new OtraCasilla(5, 0,TipoCasilla.CARCEL);
        casillas.add(carcel);
        casillas.add(new Calle(6, 100, new TituloPropiedad("SEIS", 65, 13f, 405, 400)));
        casillas.add(new OtraCasilla(7, 0,TipoCasilla.SORPRESA));
        casillas.add(new Calle(8, 100, new TituloPropiedad("OCHO", 70, 14f, 490, 450)));
        casillas.add(new Calle(9, 100, new TituloPropiedad("NUEVE", 75, 15f, 575, 500)));
        casillas.add(new OtraCasilla(10, 0,TipoCasilla.PARKING));
        casillas.add(new Calle(11, 100, new TituloPropiedad("ONCE", 80, 16f, 660, 550)));
        casillas.add(new OtraCasilla(12, 0,TipoCasilla.SORPRESA));
        casillas.add(new Calle(13, 100, new TituloPropiedad("TRECE", 85, 17f, 745, 600)));
        casillas.add(new Calle(14, 100, new TituloPropiedad("CATORCE", 90, 18f, 830, 650)));
        carcel= new OtraCasilla(15, 0,TipoCasilla.JUEZ);
        casillas.add(new Calle(16, 100, new TituloPropiedad("DIECISEIS", 95, 19f, 915, 700)));
        casillas.add(new Calle(17, 100, new TituloPropiedad("DIECISIETE", 100, 20f, 1000, 750)));
        casillas.add(new OtraCasilla(18, 0,TipoCasilla.SORPRESA));
        casillas.add(new Calle(19, 100, new TituloPropiedad("DIECINUEVE", 100, 20f, 1000, 750)));
    }   
    
    boolean esCasillaCarcel(int numeroCasilla){
       boolean rta = false;
       if(numeroCasilla == carcel.getNumeroCasilla()){
           rta = true;
       }
       return rta;
    }
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        return obtenerCasillaNumero((casilla.getNumeroCasilla() + desplazamiento) % 19);
    }
    
    public Casilla getCarcel(){
        return carcel;
    }


    
    public String toString() {
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + "}";
    }
}
    
    