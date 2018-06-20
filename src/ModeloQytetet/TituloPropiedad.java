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
public class TituloPropiedad {
    private String nombre;
    private boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    Jugador propietario;
    Casilla casilla;
    
    public TituloPropiedad(String nnombre, int nalquilerBase, float nfactorRevalorizacion, int nhipotecaBase, int nprecioEdificar ){
        nombre = nnombre;
        hipotecada = false;
        alquilerBase = nalquilerBase;
        factorRevalorizacion = nfactorRevalorizacion;
        hipotecaBase = nhipotecaBase;
        precioEdificar = nprecioEdificar;
      //  casilla.titulo = this;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    boolean getHipotecada(){
        return hipotecada;
    }
    
    int getAlquilerBase(){
        return alquilerBase;
    }
    
    float getFactorRevalorizacion(){
        return factorRevalorizacion;
    }
    
    int getHipotecaBase(){
        return hipotecaBase;
    }
    
    int getPrecioEdificar(){
        return  precioEdificar;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public Casilla getCasilla() {
        return casilla;
    }
    
 
    void cobrarAlquiler(int coste){
        //tengo q añadirle el coste a su saldo
        propietario.modificarSaldo(+coste);
    }

    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }
    
    void setHipotecada(boolean hipotecada){
        this.hipotecada=hipotecada;
    }
    
    void setPropietario(Jugador propietario){
        this.propietario = propietario;
    }
    
    boolean tengoPropietario(){
        return propietario != null;
    }
    

    /*
    
    @Override
    public String toString(){       
       return "TituloPropiedad{" + " nombre=" + nombre + ", hipotecada=" + hipotecada +
               ", alquiler base=" + Integer.toString(alquilerBase) + ", factorRevalorizacion=" + Float.toString(factorRevalorizacion) +
               ", hipotecaBase=" + Integer.toString(hipotecaBase) + ", PrecioEdificar=" + Integer.toString(precioEdificar) + "}";
               
    }*/

    @Override
    public String toString() {
        return "TituloPropiedad{" + " casilla= " + casilla.getNumeroCasilla() + ", nombre=" + nombre + ", hipotecada=" + hipotecada + 
                ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion 
                + ", hipotecaBase=" + hipotecaBase + ", precioEdificar=" + precioEdificar   + '}';
    } 
    
}
