/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
import viidensuora.Laatu;
import viidensuora.RistiNollaMuistio;

/**
 *
 * @author Aapo
 */
public class VihjeNappulanKuuntelija implements ActionListener {
    
    private RuudukonHallitsija hallitsija;
    private PeliHallitsija peliHallitsija;
    private RistiNollaMuistio muistio;
    
    /**
     *
     * @param hallitsija
     * @param peliHallitsija
     * @param muistio
     */
    public VihjeNappulanKuuntelija(RuudukonHallitsija hallitsija, PeliHallitsija peliHallitsija, RistiNollaMuistio muistio) {
        this.hallitsija = hallitsija;
        this.peliHallitsija = peliHallitsija;
        this.muistio = muistio;
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Laatu laatu;
        if (muistio.getEdellinenMerkkiRisti()) {
            laatu = Laatu.NOLLA;
        } else {
            laatu = Laatu.RISTI;
        }
        boolean onnistuiko = hallitsija.etsiVihjeet(muistio.getMerkit(), laatu);
        peliHallitsija.onkoVihjePainettu(onnistuiko);
        if (onnistuiko) {
            hallitsija.paivitaVihjeRuudukot();
            peliHallitsija.paivitaPelikentta();
            peliHallitsija.lisaaVihjekertaTunnukselle();
        }
    }
    
}
