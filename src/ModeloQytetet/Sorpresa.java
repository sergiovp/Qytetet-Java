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
public class Sorpresa {
   private  String texto;
   private TipoSorpresa tipo;
   private int valor;
   
   
   
   public Sorpresa(String ntexto, int nvalor, TipoSorpresa ntipo){
       texto = ntexto;
       tipo= ntipo;
       valor = nvalor;
       
   }
   
   String getTexto(){
       return texto;
   }
   
   TipoSorpresa getTipo(){
       return tipo;
   }
   
   int getValor(){
       return valor;
   }

    @Override
    public String toString() {
        return "\nSorpresa{" + "texto=" + texto + ", tipo=" + tipo + ", valor=" + Integer.toString(valor) + "}";
    }
   
  
  
  
  
  
  

}

