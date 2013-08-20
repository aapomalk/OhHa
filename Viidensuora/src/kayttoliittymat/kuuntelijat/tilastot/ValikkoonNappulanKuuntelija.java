/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Kuuntelee, että pitäisikö siirtyä valikkoon, ja välittää käskyn eteenpäin
 * @author aapomalk
 */
public class ValikkoonNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    /**
     * 
     * @param liittyma tallennetaan viite kuuntelijaan
     */
    public ValikkoonNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * nappulaa painettaessa käsketään käyttöliittymää palaamaan valikkoon
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.palaaValikkoon();
    }
    
}
