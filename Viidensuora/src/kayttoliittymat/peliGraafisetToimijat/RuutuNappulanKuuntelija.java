/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ruutunappulan sisältämän JButton -nappulan henkilökohtainen tapahtumankuuntelija
 * @see RuutuNappula
 * @author Aapo
 */
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
