/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloQytetet;

/**
 *
 * @author merino
 */
public abstract class Casilla {
    protected int numeroCasilla;
    protected int coste;
    //private int numHoteles;
    //private int numCasas;
    protected TipoCasilla tipo;
    //TituloPropiedad titulo = null;
    
    public Casilla(int nnumeroCasilla, int ncoste, TipoCasilla tipo){
        numeroCasilla = nnumeroCasilla;
        coste = ncoste;
        this.tipo = tipo;
    }
   /* public Casilla(int nnumeroCasilla, int ncoste, TituloPropiedad ntitulo){ //java hasta la practica 1 sesion 2 me va perfe, he avanzaao hasta mitad de la p2 s1
        numeroCasilla = nnumeroCasilla;
        coste = ncoste;
       //numHoteles =0;
        //numCasas = 0;
        //tipo = TipoCasilla.CALLE;
        //setTituloPropiedad(ntitulo);
        //titulo.casilla = this;
    }
       */ 
    
    
  /*  public Casilla(int nnumeroCasilla,int ncoste, TipoCasilla ntipo){
        numeroCasilla = nnumeroCasilla;
        coste=ncoste;
        tipo = ntipo;
    }
    */
    //Consultores y modificadores basicos
    public int getNumeroCasilla(){
        return numeroCasilla;
    }
    
        
    int getCoste(){
        return coste;
    }
        
    public TipoCasilla getTipo(){
        return tipo;
    }
    
    boolean soyEdificable(){
        return tipo== TipoCasilla.CALLE;
    }
    
    
    
    
    

    @Override
    public String toString() {
        return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", tipo=" + tipo + '}';
    }
    
}
