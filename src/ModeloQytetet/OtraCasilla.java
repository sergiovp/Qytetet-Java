/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModeloQytetet;

public class OtraCasilla extends Casilla {
   
    public OtraCasilla(int nnumeroCasilla,int ncoste, TipoCasilla ntipo){
        super(nnumeroCasilla, ncoste, ntipo);
    }
    public TipoCasilla getTipo(){
        return tipo;
    }
            
    
}
