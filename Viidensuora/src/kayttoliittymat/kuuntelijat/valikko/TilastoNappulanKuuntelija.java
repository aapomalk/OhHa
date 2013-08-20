/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.valikko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Siirrytäänkö katsomaan tilastoja?
 * @author aapomalk
 */
public class TilastoNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    public TilastoNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * käsketään käyttöliittymää menemään tilastonäkymään
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.meneTilastoNakymaan();
    }
    
}
