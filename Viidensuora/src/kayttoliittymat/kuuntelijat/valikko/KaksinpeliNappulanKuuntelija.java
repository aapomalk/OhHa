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
import tilastotJaTunnukset.Tunnus;
import tilastotJaTunnukset.TunnusPari;

/**
 *
 * @author Aapo
 */
public class KaksinpeliNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    /**
     * 
     * @param liittyma tallennetaan viite kuuntelijaan
     */
    public KaksinpeliNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    /**
     * nappulaa painettaessa käsketään käyttöliittymää siirtymään pikapeliin
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Tunnus tunnus1 = valitseTunnus("Valitse ensimmainen tunnus");
        if (tunnus1 == null) {
            return;
        }
        Tunnus tunnus2 = valitseTunnus("Valitse toinen tunnus");
        if (tunnus2 == null) {
            return;
        }
        if (tunnus1.equals(tunnus2)) {
            JOptionPane.showMessageDialog(null, "Et voi pelata samalla tunnuksella samaa tunnusta vastaan!");
            return;
        }
        boolean onnistuiko = liittyma.getTilastot().lisaaTunnusPari(tunnus1, tunnus2);
        TunnusPari pari = null;
        if (!onnistuiko) {
            for (TunnusPari tunnuspari : liittyma.getTilastot().getTunnusParit()) {
                if ((tunnuspari.getTunnus1().equals(tunnus1) || tunnuspari.getTunnus2().equals(tunnus1))
                        && (tunnuspari.getTunnus1().equals(tunnus2) || tunnuspari.getTunnus2().equals(tunnus2))) {
                    pari = tunnuspari;
                    break;
                }
            }
            if (pari == null) {
                JOptionPane.showMessageDialog(null, "Tunnusparin luominen/loytaminen epaonnistui");
                return;
            }
        } else {
            pari = liittyma.getTilastot().getTunnusParit().get(liittyma.getTilastot().getTunnusParit().size()-1);
        }
         
        
        int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko yrittaa ladata tallennuksen?");
        Tunnus ristiPelaaja = null;
        if (vastaus == 0) {
            PelitilanteenLukija lukija = this.liittyma.getPeliLoad();
            String tunnus = lukija.lataaPelitilanne("" + pari.getTunnus1().getTunnus() + "_" + pari.getTunnus2().getTunnus() + ".txt", this.liittyma.getKasittelija(), this.liittyma.getMuistio());
            
            if (tunnus.contains(" ") || tunnus.isEmpty() || tunnus == null) {
                JOptionPane.showMessageDialog(null, "Ristipelaajan tunnus oli korruptoitunut");
                return;
            } else {
                ristiPelaaja = etsiTunnus(tunnus);
            }
            liittyma.meneKaksinpeliin(false, pari, ristiPelaaja);
            return;
        } else if (vastaus == 2) {
            return;
        }
        liittyma.meneKaksinpeliin(pari, ristiPelaaja);
    }
    
    private Tunnus valitseTunnus(String kysymys) {
        if (liittyma.getTunnukset().size() < 2) {
            JOptionPane.showMessageDialog(null, "Luo ensin vahintaan kaksi tunnusta");
            return null;
        }
        String[] tunnukset = new String[liittyma.getTunnukset().size()];
        for (int i = 0; i < liittyma.getTunnukset().size(); i++) {
            tunnukset[i] = liittyma.getTunnukset().get(i).getTunnus();
        }
        
        String tunnusNimi = (String) JOptionPane.showInputDialog(liittyma.getFrame(), kysymys, "Tunnuksen valinta",
                JOptionPane.PLAIN_MESSAGE, null, tunnukset, tunnukset[0]);
        if (tunnusNimi == null) {
            return null;
        }
        Tunnus tunnus = etsiTunnus(tunnusNimi);
        return tunnus;
    }
    
    private Tunnus etsiTunnus(String tunnusNimi) {
        Tunnus tunnus = null;
        for (Tunnus apu : liittyma.getTunnukset()) {
            if (apu.getTunnus().endsWith(tunnusNimi)) {
                tunnus = apu;
            }
        }
        return tunnus;
    }
    
}
