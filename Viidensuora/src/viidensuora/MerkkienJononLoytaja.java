/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

import java.util.ArrayList;

/**
 * Peli ei päättyisi ilman kyseistä luokkaa, sekä viidensuora että vihjeet
 * löytyvät 'minun' avustuksella
 * @see vihjeToiminto.Vihje
 * @author Aapo
 */
public class MerkkienJononLoytaja {
    /**
     * Etsitään annetusta Merkkien listasta haluttuja jonoja
     * @param merkit mistä etsitään merkkien jonoja
     * @param jonoEteen merkkien jonon ensimmäinen puolikas
     * @param jonoTaakse merkkien jonon toinen puolikas, katsottaessa taakse
     * päin
     * @return löydetyt merkit, joista suunnistettaessa eteen ja taakse, löydetään
     * halutut jonot
     */
    public ArrayList<Merkki> tarkasta(ArrayList<Merkki> merkit,
            ArrayList<Laatu> jonoEteen, ArrayList<Laatu> jonoTaakse) {
        ArrayList<Merkki> loydetyt = new ArrayList<Merkki>();
        
        for (Merkki merkki : merkit) {
            if(katseleYmparille(merkit, merkki, jonoEteen, jonoTaakse)) {
                loydetyt.add(merkki);
            }
        }
        
        return loydetyt;
    }
    /**
     * Etsitään vain viimeisestä merkistä lähtien, yhteen suuntaan kerrallaan,
     * ei jaettua jonoa
     * @param merkit joista merkkien jonoa etsitään
     * @param jono mitä jonoa etsitään
     * @return true jos löytyi, false jos ei löytynyt
     */
    public boolean tarkastaViimeinen(ArrayList<Merkki> merkit, ArrayList<Laatu> jono) {
        return katseleYmparille(merkit, merkit.get(merkit.size()-1), jono);
    }
    /**
     * Tarkastetaan tietystä merkistä lähtien, eteen ja taakse suunnistaen samaan
     * aikaan
     * @param merkit joista merkkien jonoja etsitään
     * @param merkki kyseisestä merkistä lähtien kahteen suuntaan kerrallaan
     * @param jonoEteen jonon puolikas eteen, mitä etsitään
     * @param jonoTaakse jonon puolikas taaksepäin, mitä etsitään samaan aikaan
     * @return true jos molemmat jonot edestä ja takaa löytyivät, false jos ei
     */
    public boolean tarkastaMerkki(ArrayList<Merkki> merkit, Merkki merkki,
            ArrayList<Laatu> jonoEteen, ArrayList<Laatu> jonoTaakse) {
        return katseleYmparille(merkit, merkki, jonoEteen, jonoTaakse);
    }
    
    private boolean katseleYmparille(ArrayList<Merkki> merkit, Merkki merkki,
            ArrayList<Laatu> jono) {
        for (Suunta suunta : Suunta.values()) { 
            if (lahdeSuuntaan(merkit, suunta, merkki, 0, jono)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean katseleYmparille(ArrayList<Merkki> merkit, Merkki merkki,
            ArrayList<Laatu> jonoEteen, ArrayList<Laatu> jonoTaakse) {
        for (Suunta suunta : Suunta.values()) {
            if (lahdeSuuntaan(merkit, suunta, merkki, 0, jonoEteen) &&
                    lahdeSuuntaan(merkit, suuntaTaakse(suunta), merkki, 0, jonoTaakse)) {
                return true;
            }
        }
        return false;
    }
    
    private Suunta suuntaTaakse(Suunta suunta) {
        for (Suunta taakse : Suunta.values()) {
            if (taakse.getXmuutos() == (-1 * suunta.getXmuutos()) &&
                    taakse.getYmuutos() == (-1 * suunta.getYmuutos())) {
                return taakse;
            }
        }
        return suunta;
    }
    
    private boolean lahdeSuuntaan(ArrayList<Merkki> merkit, Suunta suunta,
            Merkki merkki, int askel, ArrayList<Laatu> jono) {
        if (merkki.getLaatu() == (jono.get(askel))) {
            if (askel + 1 == jono.size()) {
                return true;//paatepiste
            }
            for (Merkki seuraava : merkit) {
                if (seuraava.equals(merkki)) {
                    continue;
                }
                if ((seuraava.getX() == (merkki.getX() + suunta.getXmuutos())) 
                        && (seuraava.getY() == (merkki.getY() + suunta.getYmuutos()))) {
                    askel++;
                    if (lahdeSuuntaan(merkit, suunta, seuraava, askel, jono)) {
                        return true;//paluupolku paatepisteelta
                    }
                    askel--;
                }
            }
        }
        return false;
    }
    
}
