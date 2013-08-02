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

public class RistiNollaMuistio {
    private ArrayList<Merkki> merkit;
    private boolean edellinenMerkkiRisti;
    
    public RistiNollaMuistio() {
        this.merkit = new ArrayList<Merkki>();
        this.edellinenMerkkiRisti = false;
    }
    
    public void lisaaRisti(int x, int y) {
        if (!edellinenMerkkiRisti && !onkoRuutuVarattu(x, y)) {
            merkit.add(new Merkki(x, y, Laatu.RISTI));
            edellinenMerkkiRisti = true;
        }
    }
    
    private boolean onkoRuutuVarattu(int x, int y) {
        for (Merkki merkki : merkit) {
            if (merkki.getX() == x && merkki.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    public void lisaaNolla(int x, int y) {
        if (edellinenMerkkiRisti && !onkoRuutuVarattu(x, y)) {
            merkit.add(new Merkki(x, y, Laatu.NOLLA));
            edellinenMerkkiRisti = false;
        }
    }
    
    public int ristienMaara() {
        return merkkienMaara(Laatu.RISTI);
    }
    
    private int merkkienMaara(Laatu laatu) {
        int maara = 0;
        
        for (Merkki merkki : merkit) {
            if (merkki.getLaatu().equals(laatu)) {
                maara++;
            }
        }
        
        return maara;
    }
    
    public int nollienMaara() {
        return merkkienMaara(Laatu.NOLLA);
    }
    
    public ArrayList<Merkki> getMerkit() {
        ArrayList<Merkki> merkitKopio = new ArrayList<Merkki>();
        merkitKopio.addAll(merkit);
        return merkitKopio;
    }
    
    public boolean getEdellinenMerkkiRisti() {
        return this.edellinenMerkkiRisti;
    }
}
