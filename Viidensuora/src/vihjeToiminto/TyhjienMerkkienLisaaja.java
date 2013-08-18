/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vihjeToiminto;

import java.util.ArrayList;
import viidensuora.Laatu;
import viidensuora.Merkki;
import viidensuora.Suunta;

/**
 * Vihjetoiminnossa olennainen apuväline, jotta läpikäytävien merkkien määrä olisi
 * rajattu, lisätään valmiitten merkkien ympärille yksi 'kerros' tyhjiä merkkejä
 * @see viidensuora.Laatu
 * @author aapomalk
 */
public class TyhjienMerkkienLisaaja {
    /**
     * Lisää käyttäjän antamaan listaan tyhjiä merkkejä valmiiden merkkien viereen
     * @param merkit käyttäjän antama lista, johon lisätään tyhjiä merkkejä
     */
    public void lisaaTyhjatMerkit(ArrayList<Merkki> merkit) {
        for (int i = merkit.size()-1; i >= 0; i--) {
            lisaaTyhjaViereiseen(merkit, merkit.get(i));
        }
    }
    
    private void lisaaTyhjaViereiseen(ArrayList<Merkki> merkit, Merkki merkki) {
        for (Suunta suunta : Suunta.values()) { 
            lisaaTyhja(merkit, (merkki.getX() + suunta.getXmuutos()), (merkki.getY() + suunta.getYmuutos()));
        }
    }
    
    private void lisaaTyhja(ArrayList<Merkki> merkit, int x, int y) {
        boolean vapaa = true;
        for (Merkki merkki : merkit) {
            if (merkki.getX() == x && merkki.getY() == y) {
                vapaa = false;
            }
        }
        if (vapaa) {
            merkit.add(new Merkki(x, y, Laatu.TYHJA));
        }
    }
}
