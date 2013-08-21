/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

import java.util.ArrayList;
/**
 * Pelin olennaisin luokka, vastaa merkkien muistamisesta,
 * ja niiden säännönmukaisista sijoituksista
 * @see viidensuora.Merkki
 * @author Aapo
 */
public class RistiNollaMuistio {
    private ArrayList<Merkki> merkit;
    private boolean edellinenMerkkiRisti;
    /**
     * Konstruktori luo ArrayListin merkit ja asettaa edellinenMerkkiRisti
     * muuttujaan arvon false
     */
    public RistiNollaMuistio() {
        this.merkit = new ArrayList<Merkki>();
        this.edellinenMerkkiRisti = false;
    }
    /**
     * Lisää ristin muistioon, mikäli on ristin vuoro, ja ruutu ei ole varattu
     * @param x
     * @param y
     * @return true jos lisääminen onnituis, false jos epäonnistui
     */
    public boolean lisaaRisti(int x, int y) {
        if (!edellinenMerkkiRisti && !onkoRuutuVarattu(x, y)) {
            merkit.add(new Merkki(x, y, Laatu.RISTI));
            edellinenMerkkiRisti = true;
            return true;
        }
        return false;
    }
    
    private boolean onkoRuutuVarattu(int x, int y) {
        for (Merkki merkki : merkit) {
            if (merkki.getX() == x && merkki.getY() == y) {
                return true;
            }
        }
        return false;
    }
    /**
     * Lisää nollan muistioon, mikäli on nollan vuoro, ja ruutu ei ole varattu
     * @param x
     * @param y
     * @return true jos nollan lisääminen onnistui, false jos epäonnistui
     */
    public boolean lisaaNolla(int x, int y) {
        if (edellinenMerkkiRisti && !onkoRuutuVarattu(x, y)) {
            merkit.add(new Merkki(x, y, Laatu.NOLLA));
            edellinenMerkkiRisti = false;
            return true;
        }
        return false;
    }
    /**
     * Laskee ristien määrän private metodilla merkkienMaara(Laatu laatu)
     * @return ristienMaara, joka on laskettu metodilla merkkienMaara
     */
    public int ristienMaara() {
        return merkkienMaara(Laatu.RISTI);
    }
    /**
     * Laskee kyseisen laatuisten merkkien määrän
     * @param laatu tämäntyyppistä merkkiä etsitään
     * @return merkkien määrä
     */
    private int merkkienMaara(Laatu laatu) {
        int maara = 0;
        
        for (Merkki merkki : merkit) {
            if (merkki.getLaatu().equals(laatu)) {
                maara++;
            }
        }
        
        return maara;
    }
    /**
     * Laskee nollien määrän private metodilla merkkienMaara(Laatu laatu)
     * @return nollienMaara, joka on laskettu metodilla merkkienMaara
     */
    public int nollienMaara() {
        return merkkienMaara(Laatu.NOLLA);
    }
    /**
     * Kopioi merkit uuteen ArrayList -listaan ja palauttaa sen, jolloin
     * muistion listaa ei voi sabotoida
     * @return merkit -listan kopio
     */
    public ArrayList<Merkki> getMerkit() {
        ArrayList<Merkki> merkitKopio = new ArrayList<Merkki>();
        merkitKopio.addAll(merkit);
        return merkitKopio;
    }
    
    public boolean getEdellinenMerkkiRisti() {
        return this.edellinenMerkkiRisti;
    }
    /**
     * Ainut tapa muokata merkit -listan sisältöä, on perua viimeinen siirto
     * @return true jos peruminen onnistui, false jos merkkejä ei ollut
     */
    public boolean peruSiirto() {
        if (!this.merkit.isEmpty()) {
            this.merkit.remove(this.merkit.size()-1);
            if (this.edellinenMerkkiRisti) {
                this.edellinenMerkkiRisti = false;
            } else {
                this.edellinenMerkkiRisti = true;
            }
            return true;
        }
        return false;
    }
}
