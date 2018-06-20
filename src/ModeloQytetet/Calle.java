/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModeloQytetet;

public class Calle extends Casilla{
    private int numHoteles;
    private int numCasas;
    TituloPropiedad titulo = null;

    
    Calle(int nnumeroCasilla, int ncoste,TituloPropiedad ntitulo){
        super(nnumeroCasilla, ncoste, TipoCasilla.CALLE);
        numHoteles =0;
        numCasas = 0;
        setTituloPropiedad(ntitulo);
        titulo.casilla = this;
    }
    
    public TituloPropiedad getTitulo() {
        return titulo;
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        titulo.propietario = jugador;
        return  titulo;
    }
    
    
    
     int calcularValorHipoteca(){
        return titulo.getHipotecaBase() + (int)(numCasas*0.5 * titulo.getHipotecaBase() + numHoteles * titulo.getHipotecaBase());
    }
    
    int cancelarHipoteca(){
        int devolver=0;
        if(this.estaHipotecada()){
            titulo.setHipotecada(false);
            devolver=((int)((calcularValorHipoteca()*0.1)+calcularValorHipoteca()));
        }
        return devolver;
    }
    int cobrarAlquiler(){
        int costeAlquilerBase =  (int)(titulo.getAlquilerBase() + numCasas * 0.5 + numHoteles * 2);
        titulo.cobrarAlquiler(costeAlquilerBase);
        return costeAlquilerBase;
    }
    
    int edificarCasa(){
        setNumCasas(numCasas+1);
        int costeEdificarCasa = titulo.getPrecioEdificar();
        return costeEdificarCasa;
     
    }
    
    int edificarHotel(){
        setNumCasas(0);
        int costeEdificarHotel=titulo.getPrecioEdificar();
        return costeEdificarHotel;
    }
    
    boolean estaHipotecada(){
        return titulo.getHipotecada();    
    }

    int getCosteHipoteca(){
       ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        return cancelarHipoteca();
    }
    
    int getNumCasas(){
        return numCasas;      
    }
    
    int getNumHoteles(){
        return numHoteles;        
    }
    
    int getPrecioEdificar(){
        return titulo.getPrecioEdificar();
    }
    
    int hipotecar(){
        titulo.setHipotecada(true);
        int cantidadRecibida = calcularValorHipoteca();
        return cantidadRecibida;
    }
    
    int precioTotalComprar(){
        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int precio = getCoste() + (numCasas + numHoteles)*titulo.getPrecioEdificar();
        precio = precio + (int) (precio*titulo.getFactorRevalorizacion());
        return precio;
    }
    
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    
    boolean sePuedeEdificarCasa(){
        if(Jugador.factorEspeculador == 1)
            return numCasas <4;
        else
            return numCasas <8;
        
    }
    
    boolean sePuedeEdificarHotel(){
        if(Jugador.factorEspeculador == 1)
            return numCasas==4 && numHoteles <4 ;
        else
            return numCasas==8 && numHoteles <8 ;
    }
    
    void setNumCasas(int nuevoNumero){
        numCasas = nuevoNumero;
    }
    
    void setNumHoteles(int nuevoNumero){
        numHoteles = nuevoNumero;
    }
    
     
    public boolean tengoPropietario(){//-------------------------
        return titulo.tengoPropietario();
    }
    
    int venderTitulo(){
        int precio;
        int precioCompra = getCoste() + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        precio = (int) (precioCompra + titulo.getFactorRevalorizacion() * precioCompra);
        titulo.setPropietario(null);
        setNumCasas(0);
        setNumHoteles(0);
        return precio;
    }
    
    
    private void setTituloPropiedad(TituloPropiedad ntitulo){
        titulo = ntitulo;
        //titulo.setCasilla(this); ////////////////////////////////////////////////////////////////////////////////ESTA BIEN?????
    }
    
    private void asignarTituloPropiedad(){ //////////////////////////////////////////////////////////////////////////////////////////
        
    }

    @Override
    public String toString() {
        return "Calle{" + ", titulo=" + titulo+ "numHoteles=" + numHoteles + ", numCasas=" + numCasas  + '}';
    }
    
    
}
