/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.GraafinenKayttoliittyma;

/**
 * Olennainen osa Tunnus- ja Tunnusparitilastoja, Alasnappulan ohella hallitsee
 * näytettäviä tilastoja. Itse nappulat Graafisessakäyttöliittymässä
 * @see kayttoliittymat.GraafinenKayttoliittyma
 * @see AlasNappulanKuuntelija
 * @author Aapo
 */
public class YlosNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private Integer lahtoIndeksi;
    private int maksIndeksi;
    private boolean trueTunnuksilleFalsePareille;
    /**
     * Alustetaan kuuntelijan oliomuuttujat
     * @param liittyma käytettävä käyttöliittymä (graafinen)
     * @param lahtoIndeksi mistä indeksistä lähdetään tulostamaan tilastoja (tunnus tai tunnuspari)
     * @param maksIndeksi mikä on kyseisten tilastojen maksimi-indeksi
     * @param trueTunnuksilleFalsePareille kummista tilastoista on kyse
     */
    public YlosNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, int maksIndeksi, boolean trueTunnuksilleFalsePareille) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.maksIndeksi = maksIndeksi;
        this.trueTunnuksilleFalsePareille = trueTunnuksilleFalsePareille;
    }
    /**
     * nappia painettaessa muokataan lähtöindeksiä ja tulostetaan tilastot uudestaan
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (lahtoIndeksi < (maksIndeksi - 35)) {
            lahtoIndeksi = lahtoIndeksi + 5;
            if (trueTunnuksilleFalsePareille) {
                liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), lahtoIndeksi);
            } else {
                liittyma.tulostaTunnuspariTilastoja(lahtoIndeksi, liittyma.getFrame().getContentPane());
            }
            liittyma.getFrame().pack();
        }
    }
    
    int getLahtoIndeksi() {
        return this.lahtoIndeksi;
    }
    
    void setLahtoIndeksi(int indeksi) {
        this.lahtoIndeksi = indeksi;
    }
}
