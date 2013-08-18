/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

/**
 * Olennainen osa tilastojen järjestelijöitten toimintaa, jotta nappuloiden
 * kuuntelijoita olisi kaksitoista kappaletta vähemmän.
 * @author Aapo
 */
public enum TunnusTilastoKategoriat {
    TUNNUS(true), PELIT(true), VOITOT(true), RISTIT(true), PITUUS(true), VIHJEET(true),
    TUNNUS1_PARI(false), TUNNUS2_PARI(false), PELIT_PARI(false), TUNNUS1_VOITOT(false), TUNNUS1_RISTIT(false),
    TUNNUS2_VOITOT(false), TUNNUS2_RISTIT(false), PITUUS_PARI(false), TALLENNUKSET_PARI(false);
    
    private final boolean trueTunnusFalsePari;
    
    private TunnusTilastoKategoriat(boolean trueTunnusFalsePari) {
        this.trueTunnusFalsePari = trueTunnusFalsePari;
    }
    /**
     * 
     * @return true jos kyseessä tunnus, false jos tunnuspari
     */
    public boolean getTrueTunnusFalsePari() {
        return this.trueTunnusFalsePari;
    }
}
