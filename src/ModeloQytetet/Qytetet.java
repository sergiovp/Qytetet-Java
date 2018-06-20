/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloQytetet;
import GUIQytetet.Dado;
import com.sun.javafx.css.CalculatedValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static java.util.Collections.shuffle;
import javax.sql.rowset.serial.SerialArray;
import java.util.Hashtable;
/**
 *
 * @author merino
 */
public class Qytetet {
    public final static int MAX_JUGADORES = 4;
    public static Qytetet instance=new Qytetet();
    final static int MAX_CARTAS= 10;
    final static int MAX_CASILLAS = 20;
    final static int PRECIO_LIBERTAD = 200;
    final static int SALDO_SALIDA = 1000;
    ArrayList<Sorpresa> mazo;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private Dado dado = GUIQytetet.Dado.getInstance();
    
      private Qytetet() {
          
          mazo = new ArrayList();
          cartaActual = null;
          jugadorActual = null;
          tablero = null;
          jugadores = new ArrayList();
          dado = GUIQytetet.Dado.getInstance();
      }
    
    public static Qytetet getInstance(ArrayList<String> nuevosjugadores) {
        if (instance == null) {
            instance = new Qytetet();
        }
        return instance;
    }
    public static  Qytetet getInstance(){
        return instance;
    }
    


   

 
    

    public int getMAX_JUGADORES() {
        return MAX_JUGADORES;
    }

    public int getMAX_CARTAS() {
        return MAX_CARTAS;
    }

    public int getMAX_CASILLAS() {
        return MAX_CASILLAS;
    }

    public int getPRECIO_LIBERTAD() {
        return PRECIO_LIBERTAD;
    }

    public int getSALDO_SALIDA() {
        return SALDO_SALIDA;
    }

    public ArrayList<Sorpresa> getMazo() {
        return mazo;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Dado getDado() {
        return dado;
    }
    
    
    
    
    
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        boolean esCarcel = false;
        Casilla nuevaCasilla ;
        if(cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR){
            jugadorActual.modificarSaldo(cartaActual.getValor());
          //  System.out.println("Es de tipo pagarcobrar");
        }
        else if(cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
            esCarcel = tablero.esCasillaCarcel(cartaActual.getValor());
            
            if(esCarcel){
                encarcelarJugador();
            }
            else{
               nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                jugadorActual.actualizarPosicion(nuevaCasilla);
                return tienePropietario;
            }
            //System.out.println("Es de tipo IRACASILLA");

        }
        
        else if(this.cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
            //System.out.println("Es de tipo porcasahotel");

        }
        else if(cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for(Jugador j: jugadores){
                if(j != jugadorActual){
                    j.modificarSaldo(+cartaActual.getValor());
                    jugadorActual.modificarSaldo(-cartaActual.getValor());
                }
            }
            //System.out.println("Es de tipo porjugador");

        }
        
        else if(cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
            mazo.add(cartaActual);
              //          System.out.println("Es de tipo salircarcel");

        }
        
        else if(cartaActual.getTipo() == TipoSorpresa.CONVERTIRME){
            jugadorActual.convertirme(cartaActual.getValor());
                //        System.out.println("Es de tipo convertirme");

        }
        cartaActual = null;
        return tienePropietario;
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
        /*int aDevolver=casilla.cancelarHipoteca();
        if(jugadorActual.tengoSaldo(aDevolver)){
            jugadorActual.modificarSaldo(-aDevolver);
            return true;
        }
        return false;
        */
          boolean cancelarHipoteca = false;
        if (((Calle)casilla).estaHipotecada() && this.jugadorActual.puedoPagarHipoteca(casilla)){
            int cantidadRecibida = ((Calle)casilla).getCosteHipoteca();
            this.jugadorActual.modificarSaldo(-cantidadRecibida); 
            cancelarHipoteca = true;
        }
        return cancelarHipoteca; 
    }
    
    public boolean comprarTituloPropiedad(){
        return jugadorActual.comprarTitulo();
    }
    
    public boolean edificarCasa(Casilla casilla){
        boolean sePuedeEdificar;
        boolean puedoEdificar = false;
        int costeEdificarCasa;
        if(casilla.soyEdificable()){
            sePuedeEdificar = ((Calle)casilla).sePuedeEdificarCasa();
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);

                if(puedoEdificar){
                    costeEdificarCasa = ((Calle)casilla).edificarCasa();
                    jugadorActual.modificarSaldo(costeEdificarCasa);
                }
            }
        }
        
        return puedoEdificar;
    }
    
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar= ((Calle)casilla).sePuedeEdificarHotel();
            if(sePuedeEdificar){
                puedoEdificar= jugadorActual.puedoEdificarHotel(casilla);
                if(puedoEdificar){
                   int costeEdificarHotel=((Calle)casilla).edificarHotel();
                   jugadorActual.modificarSaldo((-costeEdificarHotel));
                }
            }
        }
        return puedoEdificar; //DUDA
    }
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    
    public boolean hipotecarPropiedad(Casilla casilla){
        //boolean puedoHipotecarPropiedad = hipotecarPropiedad(casilla);
        boolean sePuedeHipotecar;
        int cantidadRecibida;
        boolean puedoHipotecar = false;
        
        if(casilla.soyEdificable()){
            sePuedeHipotecar = !((Calle)casilla).estaHipotecada();
            if(sePuedeHipotecar){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
            
                if(puedoHipotecar){
                    cantidadRecibida = ((Calle)casilla).hipotecar();
                    
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
            }
            
        }
        return puedoHipotecar;
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
               inicializarTablero();

        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        salidaJugadores();
    } 
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false ;
        boolean tengoSaldo;
        dado = GUIQytetet.Dado.getInstance();

        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            libre = dado.nextNumber()/*tirar()*/ >5;       
        }
        else if(metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
           // libre = jugadorActual.pagarLibertad(getPRECIO_LIBERTAD());
            tengoSaldo = jugadorActual.pagarLibertad(-PRECIO_LIBERTAD);
            libre = tengoSaldo;
        }
        
        if(libre){
            jugadorActual.setEncarcelado(false);
        }
        
        return libre;
    }
    public boolean jugar(){
        
        int valorDado = Dado.getInstance().nextNumber()/*tirar()*/;
        jugadorActual.actualizarPosicion(jugadorActual.getCasillaActual());
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        boolean tienePropietario =jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(nuevaCasilla.getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }
            else if(nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){ 
                cartaActual = mazo.remove(0);
            }
        }
        return tienePropietario;
                  
                  //dame la primera es remove0
    }
    
    public ArrayList<Jugador> /*Hashtable<String, String>*/  obtenerRanking(){
 
        ArrayList<Jugador> aux = jugadores;
        Collections.sort(aux); 

      
        return aux;

    }
    
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
    //devuelve las hipotecadas si entra con true y con false las no hipotecadas
    ArrayList<TituloPropiedad> aux = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
    ArrayList<Casilla> aux2 = new ArrayList();
    for (TituloPropiedad t:aux){
        aux2.add(t.getCasilla());
    }
       return aux2;
    }
    public Jugador siguienteJugador(){
       // int a = (jugadores.indexOf(jugadorActual)+1) %(jugadores.size()-1); //en ruby solo index
        //jugadorActual = jugadores.get(a);
        //return jugadorActual;
        jugadorActual = jugadores.get((jugadores.indexOf(jugadorActual)+1)%jugadores.size());
        return jugadorActual;
    }
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = false;
        if(casilla.soyEdificable()){
            puedoVender  = jugadorActual.puedoVenderPropiedad(casilla);
            if(puedoVender){
                jugadorActual.venderPropiedad(casilla);
            }
        }
        return puedoVender;
    }
    private void encarcelarJugador(){
        Casilla casillaCarcel;
        Sorpresa carta;
        if(!jugadorActual.tengoCartaLibertad()){
            casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel((OtraCasilla)casillaCarcel);
        }
        else{
            carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }
    private void inicializarCartasSorpresa(){
        mazo = new ArrayList();
        mazo.add(new Sorpresa("Compraste juguetes para los niños pobres en navidad", -100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Tu abuelo murió, heredas 400 Euros", 400, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Avanza hasta la salida", 0, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Descansa un poco, avanza hasta el Parking", 10, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Un fan anónimo ha pagado tu fianza. Sales de la cárcel", 0 , TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa("Cada jugador debe darte 100 Euros", +100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Invitas a cenar a tus amigos. 50 por cabeza", -50, TipoSorpresa.PORJUGADOR));

     

        mazo.add(new Sorpresa("Multa por construcciones inseguras.", -80, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Degravas el IVA en todas tus propiedades!", +70, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Te hemos pillado con chanclas y calcetines,"
                + "lo sentimos, ¡Debes ir a la carcel!",  5, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Conviertemee en especulador de3000", 3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa("Conviertemee en especulador de 5000", 5000, TipoSorpresa.CONVERTIRME));
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
       /*jugadores = new ArrayList();
       for(String j: nombres){
           jugadores.add(new Jugador(j));
       }
       */
        if (nombres.size() >= 2 && nombres.size() <= MAX_JUGADORES) {
            for (String j : nombres) {
                jugadores.add(new Jugador(j));
            }
        } 
        else {
            System.out.println("Nº jugadores incorrecto");
        }
    }
       
    
    private void inicializarTablero(){
       tablero = new Tablero() ;
    }
    
    private void salidaJugadores(){
        Random r = new Random();
        
        for(Jugador j:jugadores){
            j.setCasillaActual(tablero.obtenerCasillaNumero(0));
        }
        int a = r.nextInt(jugadores.size());
        jugadorActual = jugadores.get(a);
    }

    @Override
    public String toString() {
        return "Qytetet{" + "MAX_JUGADORES=" + Integer.toString(MAX_JUGADORES) + ", MAX_CARTAS=" + Integer.toString(MAX_CARTAS) + ", MAX_CASILLAS=" 
          + Integer.toString(MAX_CASILLAS) + ", PRECIO_LIBERTAD=" + Integer.toString(PRECIO_LIBERTAD) + ", SALDO_SALIDA=" + Integer.toString(SALDO_SALIDA) 
          + ", mazo=" + mazo + ", cartaActual=" + cartaActual + ", jugadorActual=" + jugadorActual + ", tablero=" + tablero + ", jugadores=" + jugadores + ", dado=" + dado + '}';
    }
    
    
    
    
  
    
    
}
//Qytetet juego = Qytetet.getInstancia();
//System.out.println(juego.toString);