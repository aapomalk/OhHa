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
 * kuuntelee nappulaa, jota painettaessa pitäisi ruudukolle ilmestyä vihjeitä
 * @author Aapo
 */
public class VihjeNappulanKuuntelija implements ActionListener {
    
    private RuudukonHallitsija hallitsija;
    private PeliHallitsija peliHallitsija;
    private RistiNollaMuistio muistio;
    
    /**
     * tallennetaan viitteet käskytettäviin olioihin
     * @param hallitsija päivittää vihjeruudukot
     * @param peliHallitsija päivittää pelikentän ja lisää vihjekerran tunnukselle
     * @param muistio sisältää listan merkeistä, joita käytetään hyväksi vihjeiden löytämiseksi
     */
    public VihjeNappulanKuuntelija(RuudukonHallitsija hallitsija, PeliHallitsija peliHallitsija, RistiNollaMuistio muistio) {
        this.hallitsija = hallitsija;
        this.peliHallitsija = peliHallitsija;
        this.muistio = muistio;
    }
    
    /**
     * nappulaa painettiin! Vihjeet kehiin.
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
