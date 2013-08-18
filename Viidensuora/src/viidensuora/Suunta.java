/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 * Olennainen osa merkkien jonojen löytämisessä, ehdoton apu suunnistuksessa
 * @see viidensuora.MerkkienJononLoytaja
 * @author Aapo
 */
public enum Suunta {
    YLOS(0, 1), 
    YLOS_OIKEALLE(1, 1), 
    OIKEALLE(1, 0), 
    ALAS_OIKEALLE(1, -1), 
    ALAS(0, -1), 
    ALAS_VASEMMALLE(-1, -1), 
    VASEMMALLE(-1, 0), 
    YLOS_VASEMMALLE(-1, 1);
    
    private final int xMuutos;
    private final int yMuutos;
    
    
    private Suunta(int xMuutos, int yMuutos) {
        this.xMuutos = xMuutos;
        this.yMuutos = yMuutos;
        
    }
    /**
     * Kutsutaan siirryttäessä viereiseen koordinaattiin
     * @return xMuutos Palauttaa suunnalle ominaisen x-koordinaatin muutoksen
     */
    public int getXmuutos() {
        return this.xMuutos;
    }
    /**
     * Kutsutaan siirryttäessä viereiseen koordinaattiin
     * @return yMuutos Palauttaa suunnalle ominaisen y-koordinaatin muutoksen
     */
    public int getYmuutos() {
        return this.yMuutos;
    }
}
