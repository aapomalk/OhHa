/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 *
 * @author aapomalk
 */
import java.util.ArrayList;

public class ReunimmaisetKoordinaatit {

    private int pieninX;
    private int suurinX;
    private int pieninY;
    private int suurinY;

    public ReunimmaisetKoordinaatit() {
        tyhjaLista();
    }

    private void tyhjaLista() {
        pieninX = 0;
        suurinX = 0;
        pieninY = 0;
        suurinY = 0;
    }

    public void etsiKoordinaatit(ArrayList<Merkki> merkit) {
        if (merkit.isEmpty()) {
            tyhjaLista();
            return;
        }
        pieninX = merkit.get(0).getX();
        suurinX = merkit.get(0).getX();
        pieninY = merkit.get(0).getY();
        suurinY = merkit.get(0).getY();
        for (Merkki merkki : merkit) {
            vertaileKoordinaatteja(merkki);
        }
    }

    private void vertaileKoordinaatteja(Merkki merkki) {

        if (merkki.getX() > suurinX) {
            suurinX = merkki.getX();
        }

        if (merkki.getX() < pieninX) {
            pieninX = merkki.getX();
        }

        if (merkki.getY() > suurinY) {
            suurinY = merkki.getY();
        }

        if (merkki.getY() < pieninY) {
            pieninY = merkki.getY();
        }

    }
    
    public int getSuurinX() {
        return suurinX;
    }
    
    public int getPieninX() {
        return pieninX;
    }
    
    public int getSuurinY() {
        return suurinY;
    }
    
    public int getPieninY() {
        return pieninY;
    }
}
