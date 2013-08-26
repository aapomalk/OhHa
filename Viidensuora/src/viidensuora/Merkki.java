/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 * Ristinollamuistion ohella olennaisin tiedonsäilyttävä palanen, sisältää
 * koordinaatit ja laadun
 * @see viidensuora.Laatu
 * @see viidensuora.RistiNollaMuistio
 * @author Aapo
 */
public class Merkki {
    private int x;
    private int y;
    private Laatu laatu;
    /**
     * Luotaessa uusi merkki, tälle asetetaan pysyvästi koordinaatit ja tyyppi
     * @param x käyttäjän antama x-koordinaatti, joka asetetaan pysyvästi
     * @param y käyttäjän antama y-koordinaatti, joka asetetaan pysyvästi
     * @param laatu käyttäjän antama laatu, jota ei voi enää vaihtaa
     */
    public Merkki(int x, int y, Laatu laatu) {
        this.x = x;
        this.y = y;
        this.laatu = laatu;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Laatu getLaatu() {
        return this.laatu;
    }
    /**
     * @return palautetaan merkkijono muotoa "laatu x y"
     */
    @Override
    public String toString() {
        return this.laatu + " " + this.x + " " +  this.y;
    }
}
