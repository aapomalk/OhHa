/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.valikko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kayttoliittymat.GraafinenKayttoliittyma;
import tiedostojenKasittely.PelitilanteenLukija;

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
        int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko yrittaa ladata tallennuksen?");
        if (vastaus == 0) {
            PelitilanteenLukija lukija = this.liittyma.getPeliLoad();
            String tunnus = lukija.lataaPelitilanne("Pikapeli.txt", this.liittyma.getKasittelija(), this.liittyma.getMuistio());
            if (!tunnus.equals("Pikapeli")) {
                JOptionPane.showMessageDialog(null, "Tallennuksen lataaminen epaonnistui");
                return;
            }
            liittyma.menePikapeliin(false);
            return;
        } else if (vastaus == 2) {
            return;
        }
        liittyma.menePikapeliin();
    }
    
}