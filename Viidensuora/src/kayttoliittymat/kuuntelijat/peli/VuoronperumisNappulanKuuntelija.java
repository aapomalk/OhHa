/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
import viidensuora.RistiNollaMuistio;

/**
 * kuuntelee nappulaa, jota painettaessa vuoron pitäisi peruuntua
 * @author Aapo
 */
public class VuoronperumisNappulanKuuntelija implements ActionListener {

    private RistiNollaMuistio muistio;
    private PeliHallitsija hallitsija;
    private RuudukonHallitsija ruutuHallitsija;

    /**
     * tallennetaan viitteet käskytettäviin olioihin
     * @param muistio josta käsin vuoro perutaan
     * @param hallitsija päivittää infotekstin ja pelikentän
     * @param ruutuHallitsija päivittää ruudukon kirjoitukset
     */
    public VuoronperumisNappulanKuuntelija(RistiNollaMuistio muistio, PeliHallitsija hallitsija, RuudukonHallitsija ruutuHallitsija) {
        this.muistio = muistio;
        this.hallitsija = hallitsija;
        this.ruutuHallitsija = ruutuHallitsija;
    }

    /**
     * nyt nappulaa painettiin, käskytetään muita!
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti perua siirron?\nPerumisen saa kumottua ainoastaan toistamalla peruttu siirto") == 0) {
            this.muistio.peruSiirto();
            this.hallitsija.paivitaInfoteksti();
            this.ruutuHallitsija.paivitaRuudukonKirjoitusTilanteet(this.muistio.getMerkit());
            this.hallitsija.paivitaPelikentta();
        }
    }
}
