/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

/**
 * Olennainen osa tilastojen järjestelijöitten toimintaa, jotta nappuloiden
 * kuuntelijoita olisi kaksitoista kappaletta vähemmän.
 * @author Aapo
 */
public enum TunnusTilastoKategoriat {
    /**
     * Kyseessä Tunnustilaston tunnus
     */
    TUNNUS(true),
    /**
     * tunnustilaston pelien lukumäärä
     */
    PELIT(true),
    /**
     * tunnustilastojen voittojen lukumäärä
     */
    VOITOT(true),
    /**
     * tunnustilastojen ristipelien lukumäärä
     */
    RISTIT(true),
    /**
     * tunnustilastojen pelien keskimääräinen pituus
     */
    PITUUS(true),
    /**
     * tunnustilastojen vihjenapin käyttökertojen lukumäärä
     */
    VIHJEET(true),
    /**
     * tunnusparitilastojen ensimmäinen tunnus
     */
    TUNNUS1_PARI(false),
    /**
     * tunnusparitilastojen toinen tunnus
     */
    TUNNUS2_PARI(false),
    /**
     * tunnusparitilastojen pelien lukumäärä
     */
    PELIT_PARI(false),
    /**
     * tunnusparitilastojen ensimmäisen tunnuksen voitot
     */
    TUNNUS1_VOITOT(false),
    /**
     * tunnusparitilastojen ensimmäisen tunnuksen ristillä pelatut pelit
     */
    TUNNUS1_RISTIT(false),
    /**
     * tunnusparitilastojen toisen tunnuksen voitot
     */
    TUNNUS2_VOITOT(false),
    /**
     * tunnusparitilastojen toisen tunnuksen ristillä pelatut pelit
     */
    TUNNUS2_RISTIT(false),
    /**
     * tunnusparitilastojen pelattujen pelien keskimääräinen pituus
     */
    PITUUS_PARI(false),
    /**
     * tunnusparitilastojen tallennusten lukumäärä
     */
    TALLENNUKSET_PARI(false);
    
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
