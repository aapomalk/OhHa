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

    public AlasNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, YlosNappulanKuuntelija ylos) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.ylos = ylos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lahtoIndeksi = ylos.getLahtoIndeksi();
        if (lahtoIndeksi > 4) {
            lahtoIndeksi = lahtoIndeksi - 5;
            liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), lahtoIndeksi);
            liittyma.getFrame().pack();
            ylos.setLahtoIndeksi(lahtoIndeksi);
        }
    }
}
