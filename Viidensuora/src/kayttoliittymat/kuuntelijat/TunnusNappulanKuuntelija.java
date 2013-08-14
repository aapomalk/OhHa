/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

/**
 *
 * @author aapomalk
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kayttoliittymat.GraafinenKayttoliittyma;

public class TunnusNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    public TunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String tunnus = JOptionPane.showInputDialog(liittyma.getFrame(),
                "Syota haluamasi tunnus:");
        if (tunnus == null || tunnus.isEmpty()) {
            return;
        }
        liittyma.luoTunnus(tunnus);
    }
    
}
