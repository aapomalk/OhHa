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

public class YlosNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private Integer lahtoIndeksi;
    private int maksIndeksi;

    public YlosNappulanKuuntelija(GraafinenKayttoliittyma liittyma, Integer lahtoIndeksi, int maksIndeksi) {
        this.liittyma = liittyma;
        this.lahtoIndeksi = lahtoIndeksi;
        this.maksIndeksi = maksIndeksi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (lahtoIndeksi < (maksIndeksi - 35)) {
            lahtoIndeksi = lahtoIndeksi + 5;
            liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), lahtoIndeksi);
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
