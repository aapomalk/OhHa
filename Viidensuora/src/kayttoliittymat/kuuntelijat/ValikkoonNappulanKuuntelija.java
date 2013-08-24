/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Kuuntelee, että pitäisikö siirtyä valikkoon, ja välittää käskyn eteenpäin
 * @author aapomalk
 */
public class ValikkoonNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    private boolean kysytaankoTallennusta;
    /**
     * 
     * @param liittyma tallennetaan viite kuuntelijaan
     */
    public ValikkoonNappulanKuuntelija(GraafinenKayttoliittyma liittyma, boolean kysytaankoTallennusta) {
        this.liittyma = liittyma;
        this.kysytaankoTallennusta = kysytaankoTallennusta;
    }
    /**
     * ei kysyta tallennusta, koska ei olla pelissa
     * @param liittyma 
     */
    public ValikkoonNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this(liittyma, false);
    }
    /**
     * nappulaa painettaessa käsketään käyttöliittymää palaamaan valikkoon
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.kysytaankoTallennusta) {
            if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti siirtya valikkoon?\n(vihje: tallenna peli ennen valikkoon siirtymista)") != 0) {
                return;
            }
        }
        liittyma.palaaValikkoon();
    }
}
