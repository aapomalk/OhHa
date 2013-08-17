/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

/**
 *
 * @author Aapo
 */
import kayttoliittymat.peliGraafisetToimijat.RuutuNappula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RuutuNappulanKuuntelija implements ActionListener {
    private RuutuNappula ruutu;
    
    public RuutuNappulanKuuntelija(RuutuNappula ruutu) {
        this.ruutu = ruutu;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.ruutu.nappulaaPainettu();
    }
    
}
