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
import kayttoliittymat.GraafinenKayttoliittyma;

public class ValikkoonNappulanKuuntelija implements ActionListener {
    private GraafinenKayttoliittyma liittyma;
    
    public ValikkoonNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        liittyma.palaaValikkoon();
    }
    
}
