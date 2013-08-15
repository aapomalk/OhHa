/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 *
 * @author aapomalk
 */
public class NaytaTunnuksetNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    public NaytaTunnuksetNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.meneTunnusTilastoihin();
    }
}
