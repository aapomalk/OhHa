/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

import kayttoliittymat.*;
/**
 * Projektin main- luokka, kaynnistaa valitun kayttoliittyman
 * @author Aapo
 */
public class Viidensuora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        TekstiKayttoliittyma meh = new TekstiKayttoliittyma();
//        meh.kaynnista();
        
        GraafinenKayttoliittyma valikko = new GraafinenKayttoliittyma();
        valikko.run();
    }
}
