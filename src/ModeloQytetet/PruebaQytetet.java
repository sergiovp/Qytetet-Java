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
public class PruebaQytetet {
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero = new Tablero();
    
    private static void inicializarSorpresas(){
        mazo.add(new Sorpresa("Te hemos pillado con chanclas y calcetines,"
                + "lo sentimos, ¡Debes ir a la carcel!",  tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Avanza hasta la salida", 0, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Descansa un poco, avanza hasta el Parking", 10, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa("Un fan anónimo ha pagado tu fianza. Sales de la cárcel", 0 , TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa("Cada jugador debe darte 100 Euros", +100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa("Invitas a cenar a tus amigos. 50 por cabeza", -50, TipoSorpresa.PORJUGADOR));

        mazo.add(new Sorpresa("Compraste juguetes para los niños pobres en navidad", -100, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa("Tu abuelo murió, heredas 400 Euros", 400, TipoSorpresa.PAGARCOBRAR));

        mazo.add(new Sorpresa("Multa por construcciones inseguras.", -80, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa("Degravas el IVA en todas tus propiedades!", +70, TipoSorpresa.PORCASAHOTEL));        
        mazo.add(new Sorpresa("Conviertemee1 de3000", 3000, TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa("Conviertemee2 de 5000", 5000, TipoSorpresa.CONVERTIRME));
    }
    
    private static ArrayList<Sorpresa> getSorpresasPositivas(){
       ArrayList<Sorpresa> aux = new ArrayList();
       /*for(int i= 0; i<mazo.size();i++){
           if(mazo.get(i).getValor() > 0){
               aux.add(mazo.get(i));
           }
       }
       */
       for(Sorpresa s:mazo){
           if(s.getValor()>0){
               aux.add(s);
           }
       }
       return aux;
    }
    
    private ArrayList<Sorpresa> getSorpresasIrACasilla(){
        ArrayList<Sorpresa> aux = new ArrayList();
        for(Sorpresa s:mazo){
           if(s.getTipo() == TipoSorpresa.IRACASILLA){
               aux.add(s);
           }
        }
        /*
        for(int i=0;i< mazo.size();i++){
            if(mazo.get(i).getTipo() == TipoSorpresa.IRACASILLA){
                aux.add(mazo.get(i));
            }  
        }
           */
       return aux;
    }
    
    private ArrayList<Sorpresa> getSorpresaTipo(TipoSorpresa t){
         ArrayList<Sorpresa> aux = new ArrayList();
        /*for(int i=0; i< mazo.size(); i++){
           if(mazo.get(i).getTipo() == t){
               aux.add(mazo.get(i));
           }
        }*/
        for(Sorpresa s:mazo){
           if(s.getTipo() == t){
               aux.add(s);
            }
        }
           
       return aux;
    }

    
    
    
    /**
     * @param args the command line arguments
     */
   /*
    public static void main(String[] args) {
        // TODO code application logic here
       inicializarSorpresas();
       System.out.println(mazo.toString());
       //System.out.println("La prueba se ha ejecutado correctamente");
       //System.out.println(getSorpresasPositivas());
    }
    */
}
