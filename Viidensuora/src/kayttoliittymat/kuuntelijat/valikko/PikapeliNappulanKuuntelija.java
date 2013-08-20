/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.valikko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Kuuntelee, että pitäisikö siirtyä pikapeliin, ja välittää käskyn eteenpäin
 * @author aapomalk
 */
public class PikapeliNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    /**
     * 
     * @param liittyma tallennetaan viite kuuntelijaan
     */
    public PikapeliNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * nappulaa painettaessa käsketään käyttöliittymää siirtymään pikapeliin
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.menePikapeliin();
    }
    
}