/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 *
 * @author Aapo
 */
import java.util.ArrayList;

public class Tarkastaja {
    private ArrayList<Laatu> tarkastusJono;
    
    public Tarkastaja(ArrayList<Laatu> tarkastusJono) {
        this.tarkastusJono = tarkastusJono;
    }
    
    public ArrayList<Merkki> tarkasta(ArrayList<Merkki> merkit) {
        ArrayList<Merkki> loydetyt = new ArrayList<Merkki>();
        
        for (Merkki merkki : merkit) {
            if(katseleYmparille(merkit, merkki)) {
                loydetyt.add(merkki);
            }
        }
        
        return loydetyt;
    }
    
    private boolean katseleYmparille(ArrayList<Merkki> merkit, Merkki merkki) {
        for (int suunta = 0; suunta < 8; suunta++) { 
            if (lahdeSuuntaan(merkit, suunta, merkki, 0)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean lahdeSuuntaan(ArrayList<Merkki> merkit, int suunta, Merkki merkki, int askel) {
        if (merkki.getLaatu() == (tarkastusJono.get(askel))) {
            if (askel + 1 == tarkastusJono.size()) {
                return true;
            }
            for (Merkki seuraava : merkit) {
                if (seuraava.equals(merkki)) {
                    continue;
                }
                if ((seuraava.getX() == tarkastaX(merkki.getX(), suunta)) 
                        && (seuraava.getY() == tarkastaY(merkki.getY(), suunta))) {
                    askel++;
                    if (lahdeSuuntaan(merkit, suunta, seuraava, askel)) {
                        return true;
                    }
                    askel--;
                }
            }
        }
        return false;
    }
    
    private int tarkastaX(int lahtoX, int suunta) {
        if (suunta == 0 || suunta == 4) {
            return lahtoX;
        }
        if (suunta > 0 && suunta < 4) {
            return (lahtoX+1);
        }
        return (lahtoX-1);
    }
    
    private int tarkastaY(int lahtoY, int suunta) {
        if (suunta == 2 || suunta == 6) {
            return lahtoY;
        }
        if (suunta > 2 && suunta < 6) {
            return (lahtoY+1);
        }
        return (lahtoY-1);
    }
}
