/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

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

    public YlosNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, int maksIndeksi, boolean trueTunnuksilleFalsePareille) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.maksIndeksi = maksIndeksi;
        this.trueTunnuksilleFalsePareille = trueTunnuksilleFalsePareille;
    }

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
    
    public int getLahtoIndeksi() {
        return this.lahtoIndeksi;
    }
    
    public void setLahtoIndeksi(int indeksi) {
        this.lahtoIndeksi = indeksi;
    }
}
