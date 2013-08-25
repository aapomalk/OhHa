/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import kayttoliittymat.peliGraafisetToimijat.RuudukonHallitsija;
/**
 *
 * @author aapomalk
 */
public class VuoronvaihtoNappulanKuuntelija implements ActionListener {
    
    private RuudukonHallitsija hallitsija;
    private PeliHallitsija peliHallitsija;
    
    public VuoronvaihtoNappulanKuuntelija(RuudukonHallitsija hallitsija, PeliHallitsija peliHallitsija) {
        this.hallitsija = hallitsija;
        this.peliHallitsija = peliHallitsija;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        hallitsija.vaihdaVuoroa();
        peliHallitsija.paivitaInfoteksti();
        peliHallitsija.onkoVihjePainettu(false);
    }
    
}
