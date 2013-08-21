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
    /**
     * Konstruktorissa asetetaan kuuntelijan sitältämään RuutuNappulaan käyttäjän syöte
     * @param ruutu käyttäjän syöttämä RuutuNappula, joka tallennetaan kuuntelijan sisään
     */
    public RuutuNappulanKuuntelija(RuutuNappula ruutu) {
        this.ruutu = ruutu;
    }
    /**
     * Kuuntelija saa signaalin, kun nappulaa, jonka sisällä se on painetaan
     * @param ae nappulan syöttämä tapahtumakoodi, jota ei käsitellä, koska
     * tapahtumia on käytännössä vain yksi
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.ruutu.nappulaaPainettu();
    }
    
}
