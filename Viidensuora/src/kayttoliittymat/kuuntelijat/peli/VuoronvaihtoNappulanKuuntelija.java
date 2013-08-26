/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
/**
 *
 * @author aapomalk
 */
public class VuoronvaihtoNappulanKuuntelija implements ActionListener, KeyListener {
    
    private RuudukonHallitsija hallitsija;
    private PeliHallitsija peliHallitsija;
    
    /**
     *
     * @param hallitsija
     * @param peliHallitsija
     */
    public VuoronvaihtoNappulanKuuntelija(RuudukonHallitsija hallitsija, PeliHallitsija peliHallitsija) {
        this.hallitsija = hallitsija;
        this.peliHallitsija = peliHallitsija;
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        vaihdaVuoroa();
    }
    
    private void vaihdaVuoroa() {
        hallitsija.vaihdaVuoroa();
        peliHallitsija.paivitaInfoteksti();
        peliHallitsija.onkoVihjePainettu(false);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode()==KeyEvent.VK_V) {
            vaihdaVuoroa();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
