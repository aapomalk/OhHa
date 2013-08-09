/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

/**
 *
 * @author Aapo
 */
public class Merkki {
    private int x;
    private int y;
    private Laatu laatu;
    
    public Merkki(int x, int y, viidensuora.Laatu laatu) {
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
    
    @Override
    public String toString() {
        return this.laatu + " " + this.x + " " +  this.y;
    }
}
