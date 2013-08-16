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
import kayttoliittymat.GraafinenKayttoliittyma;

public class AlasNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private Integer lahtoIndeksi;
    private YlosNappulanKuuntelija ylos;
    private boolean trueTunnuksilleFalsePareille;

    public AlasNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, YlosNappulanKuuntelija ylos, boolean trueTunnuksilleFalsePareille) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.ylos = ylos;
        this.trueTunnuksilleFalsePareille = trueTunnuksilleFalsePareille;
    }

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
