/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Ja nyt sitten tunnusten tilastoja, vai?
 * @author aapomalk
 */
public class NaytaTunnuksetNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    /**
     *
     * @param liittyma käskytettävä käyttöliittymä
     */
    public NaytaTunnuksetNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * käsketään käyttöliittymää menemään tunnusten tilastoihin
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.meneTunnusTilastoihin();
    }
}
