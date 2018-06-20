/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import ModeloQytetet.MetodoSalirCarcel;
import ModeloQytetet.Qytetet;
import ModeloQytetet.TipoCasilla;
/**
 *
 * @author merino
 */
public class VistaQytetet extends javax.swing.JPanel {

    /**
     * Creates new form VistaQytetet
     */
    public VistaQytetet() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaJugador1 = new GUIQytetet.VistaJugador();
        vistaCasilla1 = new GUIQytetet.VistaCasilla();
        vistaCartaSorpresa1 = new GUIQytetet.VistaCartaSorpresa();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaCasilla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vistaJugador1, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
            .addComponent(vistaCartaSorpresa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vistaJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vistaCasilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vistaCartaSorpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUIQytetet.VistaCartaSorpresa vistaCartaSorpresa1;
    private GUIQytetet.VistaCasilla vistaCasilla1;
    private GUIQytetet.VistaJugador vistaJugador1;
    // End of variables declaration//GEN-END:variables
public void actualizar(Qytetet aux){
       vistaCasilla1.actualizar(aux.getJugadorActual().getCasillaActual().toString());
       if(aux.getCartaActual() != null)
            vistaCartaSorpresa1.actualizar(aux.getCartaActual().toString());
       else
            vistaCartaSorpresa1.actualizar("No hay carta actual\n");

       
       vistaJugador1.actualizar(aux.getJugadorActual().toString());
       this.repaint();
       this.revalidate();
    }
}
