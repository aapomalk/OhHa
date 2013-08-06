/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vihjeToiminto;

/**
 *
 * @author aapomalk
 */
import java.util.ArrayList;
import viidensuora.Laatu;
import viidensuora.Merkki;
import viidensuora.Suunta;

public class TyhjienMerkkienLisaaja {
    
    public void lisaaTyhjatMerkit(ArrayList<Merkki> merkit) {
        for (int i = merkit.size()-1; i >= 0; i--) {
            lisaaTyhjaViereiseen(merkit, merkit.get(i));
        }
    }
    
    private void lisaaTyhjaViereiseen(ArrayList<Merkki> merkit, Merkki merkki) {
        for (int i = 0; i < 8; i++) { 
            Suunta suunta = muutaNumeroSuunnaksi(i);
            lisaaTyhja(merkit, suunnistaViereinenX(merkki.getX(), suunta), suunnistaViereinenY(merkki.getY(), suunta));
        }
    }
    
    protected int suunnistaViereinenX(int lahtoX, Suunta suunta) {
        if (suunta.getSuuntaArvo() == 0 || suunta.getSuuntaArvo() == 4) {
            return lahtoX;
        }
        if (suunta.getSuuntaArvo() > 0 && suunta.getSuuntaArvo() < 4) {
            return (lahtoX+1);
        }
        return (lahtoX-1);
    }
    
    protected int suunnistaViereinenY(int lahtoY, Suunta suunta) {
        if (suunta.getSuuntaArvo() == 2 || suunta.getSuuntaArvo() == 6) {
            return lahtoY;
        }
        if (suunta.getSuuntaArvo() > 2 && suunta.getSuuntaArvo() < 6) {
            return (lahtoY+1);
        }
        return (lahtoY-1);
    }
    
    protected Suunta muutaNumeroSuunnaksi(int i) {
        Suunta suunta = Suunta.ALAS;
        for (Suunta s : Suunta.values()) {
            if (s.getSuuntaArvo() == i) {
                suunta = s;
            }
        }
        return suunta;
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
