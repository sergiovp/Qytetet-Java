/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModeloQytetet;


public class Especulador extends Jugador{
    static int factorEspeculador = 2;
    int fianza;
    
    
    protected Especulador(Jugador jugador, int fianza){
        super(jugador);//con esto se llama al constructor de copia
        this.fianza = fianza; 
    }
    
    protected void pagarImpuestos(int cantidad){
        modificarSaldo(-(cantidad/2));
    }
    
    protected void irACarcel(Casilla casilla){
        if(pagarFianza(fianza)){
            modificarSaldo(-fianza);
        }
        else{
            setCasillaActual(casilla); //desde la clase qytetet
            setEncarcelado(true);
        }
    }
    
    protected Especulador convertirme(int fianza){
        return this;
    }
    
    boolean pagarFianza(int cantidad){
        if(cantidad < getSaldo()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Especulador{" +super.toString()+ "fianza=" + fianza + "}\n";
    }
    
}
