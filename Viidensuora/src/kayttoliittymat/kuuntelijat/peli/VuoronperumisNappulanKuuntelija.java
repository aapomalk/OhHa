/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import kayttoliittymat.GraafinenKayttoliittyma;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
import viidensuora.RistiNollaMuistio;

/**
 *
 * @author Aapo
 */
public class VuoronperumisNappulanKuuntelija implements ActionListener {

    private RistiNollaMuistio muistio;
    private PeliHallitsija hallitsija;
    private RuudukonHallitsija ruutuHallitsija;

    /**
     *
     * @param muistio
     * @param hallitsija
     * @param ruutuHallitsija
     */
    public VuoronperumisNappulanKuuntelija(RistiNollaMuistio muistio, PeliHallitsija hallitsija, RuudukonHallitsija ruutuHallitsija) {
        this.muistio = muistio;
        this.hallitsija = hallitsija;
        this.ruutuHallitsija = ruutuHallitsija;
    }

    /**
     *
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
