/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
/**
 *
 * @author aapomalk
 */
public class VuoronvaihtoNappulanKuuntelija implements ActionListener {
    
    private RuudukonHallitsija hallitsija;
    
    public VuoronvaihtoNappulanKuuntelija(RuudukonHallitsija hallitsija) {
        this.hallitsija = hallitsija;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        hallitsija.vaihdaVuoroa();
    }
    
}
