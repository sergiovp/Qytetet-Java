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
public class Jugador implements Comparable {
    private boolean encarcelado;
    private String nombre;
    private int saldo;
    private Casilla casillaActual;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades;
    static int factorEspeculador =1; 
            
    protected Jugador(String nombre) {
        this.encarcelado = false;
        this.nombre = nombre;
        //this.casillaActual = ;
        this.cartaLibertad = null;
        propiedades = new ArrayList();
        saldo=7500;
    }
    
    protected Jugador(Jugador otro){
        if(this != otro){
            encarcelado = otro.getEncarcelado();
            nombre = otro.getNombre();
            saldo = otro.getSaldo();
            casillaActual = otro.getCasillaActual();
            cartaLibertad = otro.cartaLibertad;
            propiedades = otro.getPropiedades();
        }
    }

    @Override
    public int compareTo(Object o){
        Jugador otro = (Jugador) o;
        if(otro.obtenerCapital() < this.obtenerCapital())
            return 1;
        else if(otro.obtenerCapital() > this.obtenerCapital())
            return -1;
        else
            return 0;
    
    }
    
    int getFactorEspeculador(){
        return factorEspeculador;
    }
    protected void pagarImpuestos(int cantidad){
        modificarSaldo(-cantidad);
    }
    
    protected Especulador convertirme(int fianza){
        Especulador aux;
        aux = (Especulador)this;
        aux.fianza = fianza;
        return aux;
    }
    
   
    public int getSaldo(){
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }
    
    
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }
    
    public boolean tengoPropiedades(){        
        return !propiedades.isEmpty();
    }
    
    protected boolean actualizarPosicion(Casilla casilla){
       boolean tengoPropietario=false;
       int costeAlquiler;
       int coste;
       if(casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla()){
           modificarSaldo(Qytetet.SALDO_SALIDA);
       }
        setCasillaActual(casilla);
        
        if(casilla.soyEdificable()){
            tengoPropietario = ((Calle)casilla).tengoPropietario();
           
            if(tengoPropietario){
                //tengoPropietario = true;
                encarcelado = ((Calle)casilla).propietarioEncarcelado();
                
               if(!encarcelado){
                    costeAlquiler = ((Calle)casilla).cobrarAlquiler();
                    modificarSaldo(-costeAlquiler);
               }
            } ///
        }
        
        if(casilla.getTipo() == TipoCasilla.IMPUESTO){
            coste = casilla.getCoste();
            pagarImpuestos(coste);
        }
        return tengoPropietario;
    }
    
    boolean comprarTitulo(){
         boolean tengoPropietario;
         boolean puedoComprar = false;
         int costeCompra;
         TituloPropiedad titulo;
         if(casillaActual.soyEdificable()){
            tengoPropietario = ((Calle)casillaActual).tengoPropietario();
            if(!tengoPropietario){
                costeCompra = casillaActual.getCoste();
                if(costeCompra < saldo){ //si acaba con saldo 0 acaba la partida
                    titulo = ((Calle)casillaActual).asignarPropietario(this);
                    propiedades.add(titulo);
                    modificarSaldo(-costeCompra);
                    puedoComprar =true;
                }
            }
            
        }
        return puedoComprar;
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa aux =cartaLibertad;
        cartaLibertad = null;
        return aux;
    }
    
    void irACarcel(OtraCasilla carcel){ //se le pasa por parametro la casilla de la carcel
        setCasillaActual(carcel); //desde la clase qytetet
        setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        saldo +=cantidad;
    }
    
    
    public int obtenerCapital(){ //-----------------------------
        //funcion al acabar el juego no solo es el saldo es su dinero mas lo que le costaron las
        //calles menos el coste q le darian si la ha hipotecado 
        int total=saldo;
        for(TituloPropiedad p:propiedades){
            total += p.getCasilla().getCoste();
            if(((Calle)p.getCasilla()).estaHipotecada()){
                total -= p.getHipotecaBase();
            }//definir un metodo en titulopropiedad 
        }
        return total;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> aux= new ArrayList();
        for(TituloPropiedad p:propiedades){
            if(hipotecada){ //añadimos las propiedades hipotecadas
                aux.add(p);
            }
            else{ //añadimos las propiedades NO hipotecadas
                aux.add(p);
            }
        }
        return aux;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo(numeroTotal*cantidad);
    }
    
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        
        if(tengoSaldo){
            modificarSaldo(cantidad);
        }
        return tengoSaldo;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
       boolean esMia = esDeMipropiedad(casilla);
       int costeEdificarCasa;
       boolean tengoSaldo = false;
       if(esMia){
           costeEdificarCasa = ((Calle)casilla).getPrecioEdificar();
           tengoSaldo = tengoSaldo(costeEdificarCasa);
       }
       return tengoSaldo;
    }
    
    boolean puedoEdificarHotel(Casilla casilla){
 //DUDA REVISAR Y PREGUNTAR ////////////////////////////////////////////////////////////////7
       /* boolean tengoSaldo=false;
        if (esDeMipropiedad(casilla)){
            int costeEdificarHotel=casilla.getPrecioEdificar();
            tengoSaldo=tengoSaldo(costeEdificarHotel);
        }
        return tengoSaldo;   */
        boolean puedoEdificar = false;
        boolean esmia = this.esDeMipropiedad(casilla);
        boolean tengosaldo = false;

        if (esmia) {
            int costeEdificarhotel = ((Calle)casilla).getTitulo().getPrecioEdificar();
            tengosaldo = this.tengoSaldo(costeEdificarhotel);
        }

        if (esmia && tengosaldo) {

            puedoEdificar = true;

        }

        return puedoEdificar;
        }
    
    boolean puedoHipotecar(Casilla casilla){
        boolean esMia = esDeMipropiedad(casilla);
        return esMia;
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        //return false; ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        return ((Calle)casilla).getTitulo().getHipotecaBase() < saldo;
    }
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean esMia = esDeMipropiedad(casilla);
        boolean hipotecada = ((Calle)casilla).estaHipotecada();
        
        if(esMia && !hipotecada){ 
            return true;
        }
        return false;
    }
    
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = carta;
    }
    void setCasillaActual(Casilla casilla){
       casillaActual = casilla;
    }
    void setEncarcelado(boolean Encarcelado){
        encarcelado = Encarcelado;
    }
    
    boolean tengoCartaLibertad(){
        return cartaLibertad != null;
    }
    void venderPropiedad(Casilla casilla){
        int precioVenta  = ((Calle)casilla).venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    private int cuantasCasasHotelesTengo(){
        int rta = 0;
        for(TituloPropiedad p:propiedades){
            rta += ((Calle)p.getCasilla()).getNumCasas()+ ((Calle)p.getCasilla()).getNumHoteles();
        }
        return rta;
    }
    private void eliminarDeMisPropiedades(Casilla casilla){
        for(TituloPropiedad t:propiedades){
            if(t==((Calle)casilla).getTitulo()){
                propiedades.remove(t);
            }
        }
    }
    private boolean esDeMipropiedad(Casilla casilla){
        for(TituloPropiedad p: propiedades){
            if(casilla == p.getCasilla()){
                return true;
            }
        }
        return false;
    }
    private boolean tengoSaldo(int cantidad){
        return cantidad<=saldo;
    }  

    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", casillaActual=" + casillaActual + ", cartaLibertad=" + cartaLibertad + ", propiedades=" + propiedades + '}';
    }
    

}
