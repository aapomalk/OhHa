/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 *
 * @author Aapo
 */
public enum Suunta {
    YLOS(4), YLOS_OIKEALLE(3), OIKEALLE(2), ALAS_OIKEALLE(1), 
    ALAS(0), ALAS_VASEMMALLE(7), VASEMMALLE(6), YLOS_VASEMMALLE(5);
    
    private final int suuntaArvo;
    
    private Suunta(int suuntaArvo) {
        this.suuntaArvo = suuntaArvo;
    }
    
    public int getSuuntaArvo() {
        return this.suuntaArvo;
    }
}
