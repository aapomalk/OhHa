/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.valikko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Haluat siis yrittää syöttää uutta tunnusta?
 * @author aapomalk
 */
public class TunnusNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    /**
     *
     * @param liittyma
     */
    public TunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * kysytään käyttäjältä tunnuksen nimeä ja käsketään käyttöliittymän luoda uusi tunnus
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String tunnus = JOptionPane.showInputDialog(liittyma.getFrame(),
                "Syota haluamasi tunnus:");
        if (tunnus == null || tunnus.isEmpty()) {
            return;
        }
        liittyma.luoTunnus(tunnus);
    }
    
}
