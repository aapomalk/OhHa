/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Vai että tunnusparien tilastoja pitäisi nähdä?
 * @author aapomalk
 */
public class NaytaTunnusparitNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    /**
     *
     * @param liittyma käskytettävä käyttöliittymä
     */
    public NaytaTunnusparitNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * käsketään käyttöliittymää menemään tunnusparien tilastoihin
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.meneTunnuspariTilastoihin();
    }
}
