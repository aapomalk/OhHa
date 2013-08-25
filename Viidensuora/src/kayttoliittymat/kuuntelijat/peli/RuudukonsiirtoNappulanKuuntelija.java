/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
import viidensuora.ReunimmaisetKoordinaatit;
import viidensuora.RistiNollaMuistio;
import viidensuora.Suunta;

/**
 *
 * @author Aapo
 */
public class RuudukonsiirtoNappulanKuuntelija implements ActionListener {
    
    private static boolean vihje = false;
    
    public static void asetaVihje(boolean vihjePainettu) {
        vihje = vihjePainettu;
    }

    private RistiNollaMuistio muistio;
    private PeliHallitsija hallitsija;
    private RuudukonHallitsija ruutuHallitsija;
    private ReunimmaisetKoordinaatit rajaaja;
    private Suunta suunta;

    public RuudukonsiirtoNappulanKuuntelija(RistiNollaMuistio muistio, PeliHallitsija hallitsija, RuudukonHallitsija ruutuHallitsija, Suunta suunta) {
        this.muistio = muistio;
        this.hallitsija = hallitsija;
        this.ruutuHallitsija = ruutuHallitsija;
        this.rajaaja = new ReunimmaisetKoordinaatit();
        this.suunta = suunta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.rajaaja.etsiKoordinaatit(muistio.getMerkit());
        siirraRuudukkoa();
        this.ruutuHallitsija.paivitaRuudukonKirjoitusTilanteet(this.muistio.getMerkit());
        if (vihje) {
            this.ruutuHallitsija.paivitaVihjeRuudukot();
        }
        this.hallitsija.paivitaPelikentta();
    }
    
    private void siirraRuudukkoa() {
        if (suunta.getXmuutos() != 0) {
            if (suunta.getXmuutos() < 0) {
                if (rajaaja.getSuurinX() > this.ruutuHallitsija.getRuudukonSuurinX() - 4) {
                    this.ruutuHallitsija.siirraRuudukkoaXsuunnassa(1);
                }
            } else if (rajaaja.getPieninX() < this.ruutuHallitsija.getRuudukonPieninX() + 4) {
                this.ruutuHallitsija.siirraRuudukkoaXsuunnassa(-1);
            }
        }
        
        if (suunta.getYmuutos() != 0) {
            if (suunta.getYmuutos() < 0) {
                if (rajaaja.getPieninY() < this.ruutuHallitsija.getRuudukonPieninY() + 4) {
                    this.ruutuHallitsija.siirraRuudukkoaYsuunnassa(-1);
                }
            } else if (rajaaja.getSuurinY() > this.ruutuHallitsija.getRuudukonSuurinY() - 4) {
                this.ruutuHallitsija.siirraRuudukkoaYsuunnassa(1);
            }
        }
    }
}
