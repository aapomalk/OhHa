/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

/**
 *
 * @author Aapo
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import kayttoliittymat.GraafinenKayttoliittyma;
import tunnustenJaParienJarjestelijat.*;

public class JarjestaTunnusNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private boolean kumpiOliViimeksi;
    private TunnusTilastoKategoriat kategoria;

    public JarjestaTunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
        kumpiOliViimeksi = false;
        kategoria = TunnusTilastoKategoriat.TUNNUS;
    }
    
    public JarjestaTunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma,
            TunnusTilastoKategoriat kategoria) {
        this(liittyma);
        this.kategoria = kategoria;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (kumpiOliViimeksi) {
            jarjestaOikein();
            kumpiOliViimeksi = false;
        } else {
            jarjestaOikein();
            Collections.reverse(liittyma.getTunnukset());
            kumpiOliViimeksi = true;
        }
        
        liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), -1);
        liittyma.getFrame().pack();
    }
    
    private void jarjestaOikein() {
        if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS)) {
            Collections.sort(liittyma.getTunnukset());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PELIT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetPelienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PITUUS)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetPelipituudenMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.RISTIT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetRistienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.VIHJEET)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetVihjeidenMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.VOITOT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetVoittojenMukaanJarjestykseen());
        }
    }
}