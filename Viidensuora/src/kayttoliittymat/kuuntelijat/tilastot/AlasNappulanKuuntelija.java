/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Olennainen osa Tunnus- ja Tunnusparitilastoja, Ylösnappulan ohella hallitsee
 * näytettäviä tilastoja. Itse nappulat Graafisessakäyttöliittymässä
 * @see kayttoliittymat.GraafinenKayttoliittyma
 * @see YlosNappulanKuuntelija
 * @author Aapo
 */
public class AlasNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private Integer lahtoIndeksi;
    private YlosNappulanKuuntelija ylos;
    private boolean trueTunnuksilleFalsePareille;
    /**
     * tallennetaan käyttöliittymän viite, sekä tilastojen lähtöindeksi, 
     * vastaava ylösnappulan viite ja kummista tilastoista on kyse
     * @param liittyma käyttäjän syöttämä graafisen käyttöliittymän viite
     * @param lahtoIndeksi käyttäjän syöttämä lähtöindeksi
     * @param ylos käyttäjän syöttämä vastaaava ylösnappulan kuuntelija
     * @param trueTunnuksilleFalsePareille käyttäjä syöttää true tai false, 
     * riippuen kummista tilastoista on kyse
     */
    public AlasNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, YlosNappulanKuuntelija ylos, boolean trueTunnuksilleFalsePareille) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.ylos = ylos;
        this.trueTunnuksilleFalsePareille = trueTunnuksilleFalsePareille;
    }
    /**
     * siirrytään viisi pykälää "alas" tilastoissa, mikäli nykyinen lähtöindeksi on yli nolla
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        lahtoIndeksi = ylos.getLahtoIndeksi();
        if (lahtoIndeksi > 0) {
            lahtoIndeksi = lahtoIndeksi - 5;
            if (lahtoIndeksi == 0) {
                lahtoIndeksi = -1;
            }
            if (this.trueTunnuksilleFalsePareille) {
                liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), lahtoIndeksi);
            } else {
                liittyma.tulostaTunnuspariTilastoja(lahtoIndeksi, liittyma.getFrame().getContentPane());
            }
            liittyma.getFrame().pack();
            ylos.setLahtoIndeksi(lahtoIndeksi);
        }
    }
}
