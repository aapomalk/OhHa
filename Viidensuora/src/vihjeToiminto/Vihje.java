/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vihjeToiminto;

import java.util.ArrayList;
import viidensuora.Laatu;
import viidensuora.Merkki;
import viidensuora.MerkkienJononLoytaja;

/**
 * Nimensä mukaisesti luokan ainut tarkoitus on antaa vihjeitä pisteyttämällä
 * tyhjiä koordinaatteja
 * @see vihjeToiminto.TyhjienMerkkienLisaaja
 * @see viidensuora.Laatu
 * @see viidensuora.MerkkienJononLoytaja
 * @author aapomalk
 */
public class Vihje {

    private TyhjienMerkkienLisaaja taydentaja;
    private ArrayList<Merkki> ehkaHyodyllinen;
    private ArrayList<Merkki> rakennaHyokkays;
    private ArrayList<Merkki> varmaVoitto;
    private ArrayList<Merkki> hairitseVastustajaa;
    private ArrayList<Merkki> varoVastustajaa;
    private ArrayList<Merkki> taytyyEstaa;
    private ArrayList<Merkki> hyodyllistaJaHairintaa;
    private ArrayList<Merkki> hyokkaystaJaVaromista;
    private ArrayList<Merkki> voittamistaJaEstamista;
    private MerkkienJononLoytaja etsija;
    private ArrayList<ArrayList<Laatu>> puolikkaatJonotRistille;
    private ArrayList<ArrayList<Laatu>> puolikkaatJonotNollalle;
    private ArrayList<Integer> pisteytyslista;
    /**
     * Alustetaan kaikki tarvittavat komponentit: MerkkienJononloytaja,
     * kaikki puolikkaat jonot sekä ristille, että nollalle, nollataan
     * vihjelistat ja luodaan pisteytyslistat
     */
    public Vihje() {
        etsija = new MerkkienJononLoytaja();
        puolikkaatJonotRistille = new ArrayList<ArrayList<Laatu>>();
        puolikkaatJonotNollalle = new ArrayList<ArrayList<Laatu>>();
        taydentaja = new TyhjienMerkkienLisaaja();
        alustaLaatuJonot();
        nollaaListat();
        pisteytyslista();
    }
    
    private void pisteytyslista() {
        pisteytyslista = new ArrayList<Integer>();
        
        pisteytyslista.add(0);//        jonotMerkkeina.add("_");
        pisteytyslista.add(1);//        jonotMerkkeina.add("_x_");
        pisteytyslista.add(1);//        jonotMerkkeina.add("__x_");
        pisteytyslista.add(3);//        jonotMerkkeina.add("_xx_");
        pisteytyslista.add(2);//        jonotMerkkeina.add("__xx_");
        pisteytyslista.add(5);//        jonotMerkkeina.add("_xxx_");
        pisteytyslista.add(4);//        jonotMerkkeina.add("__xxx_");
        pisteytyslista.add(7);//        jonotMerkkeina.add("_xxxx_");
        pisteytyslista.add(0);//        jonotMerkkeina.add("_xo");
        pisteytyslista.add(2);//        jonotMerkkeina.add("_xxo");
        pisteytyslista.add(4);//        jonotMerkkeina.add("_xxxo");
        pisteytyslista.add(6);//        jonotMerkkeina.add("_xxxxo");
        pisteytyslista.add(0);//        jonotMerkkeina.add("__xo");
        pisteytyslista.add(2);//        jonotMerkkeina.add("__xxo");
        pisteytyslista.add(4);//        jonotMerkkeina.add("__xxxo");
    }
    /**
     * Asettaa vihjelistoihin löydetyt merkit pisteytyksen mukaan
     * @param merkit näistä etsitään vihjattavia merkkien jonoja
     * @param laatu kumpi on hyökkäämässä, vaikuttaa pisteytykseen
     */
    public void lisaaVihjeetListoihin(ArrayList<Merkki> merkit, Laatu laatu) {
        if (merkit.isEmpty()) {
            return;
        }
        nollaaListat();
        int indeksi = merkit.size() - 1;
        this.taydentaja.lisaaTyhjatMerkit(merkit);
        this.taydentaja.lisaaTyhjatMerkit(merkit);
        for (int i = indeksi; i < merkit.size(); i++) {
            tarkastaLaatu(merkit.get(i), merkit, laatu);
        }
    }
    
    private void tarkastaLaatu(Merkki merkki, ArrayList<Merkki> merkit, Laatu laatu) {
        if (laatu.equals(Laatu.RISTI)) {
            loytyykoMerkistaVihjepaikka(merkki, merkit, true);
        } else if (laatu.equals(Laatu.NOLLA)) {
            loytyykoMerkistaVihjepaikka(merkki, merkit, false);
        }
    }
    
    private void loytyykoMerkistaVihjepaikka(Merkki merkki, ArrayList<Merkki> merkit, boolean onkoRistille) {
        
        for (int i = 0; i < puolikkaatJonotRistille.size(); i++) {
            for (int j = i; j < puolikkaatJonotNollalle.size(); j++) {
                int arvo = this.pisteytyslista.get(i) + this.pisteytyslista.get(j);
                if (arvo > 0) {
                    boolean risti = this.etsija.tarkastaMerkki(merkit, merkki, puolikkaatJonotRistille.get(i), puolikkaatJonotRistille.get(j));
                    boolean nolla = this.etsija.tarkastaMerkki(merkit, merkki, puolikkaatJonotNollalle.get(i), puolikkaatJonotNollalle.get(j));
                    if (arvo == 1) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.ehkaHyodyllinen.add(merkki);
                        }
                    } else if (arvo == 2) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.ehkaHyodyllinen.add(merkki);
                        }
                        if ((nolla && onkoRistille) || (risti && !onkoRistille)) {
                            this.hairitseVastustajaa.add(merkki);
                        }
                    } else if (arvo == 3) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.rakennaHyokkays.add(merkki);
                        }
                        if ((nolla && onkoRistille) || (risti && !onkoRistille)) {
                            this.hairitseVastustajaa.add(merkki);
                        }
                    } else if (arvo == 4) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.rakennaHyokkays.add(merkki);
                        }
                        if ((nolla && onkoRistille) || (risti && !onkoRistille)) {
                            this.varoVastustajaa.add(merkki);
                        }
                    } else if (arvo == 5) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.varmaVoitto.add(merkki);
                        }
                        if ((nolla && onkoRistille) || (risti && !onkoRistille)) {
                            this.varoVastustajaa.add(merkki);
                        }
                    } else if (arvo >= 6) {
                        if ((risti && onkoRistille) || (nolla && !onkoRistille)) {
                            this.varmaVoitto.add(merkki);
                        }
                        if ((nolla && onkoRistille) || (risti && !onkoRistille)) {
                            this.taytyyEstaa.add(merkki);
                        }
                    }
                }
            }
        }
        
        ylennaMoninkertaisia();
    }
    
    private void ylennaMoninkertaisia() {
        ylennaTietystaKategoriasta(this.ehkaHyodyllinen, this.rakennaHyokkays);
        ylennaTietystaKategoriasta(this.rakennaHyokkays, this.varmaVoitto);
        ylennaTietystaKategoriasta(this.hairitseVastustajaa, this.varoVastustajaa);
        ylennaTietystaKategoriasta(this.varoVastustajaa, this.taytyyEstaa);
        ArrayList<Merkki> apu = new ArrayList<Merkki>();
        apu.addAll(ehkaHyodyllinen);
        apu.addAll(hairitseVastustajaa);
        ylennaTietystaKategoriasta(apu, this.hyodyllistaJaHairintaa);
        apu.clear();
        apu.addAll(rakennaHyokkays);
        apu.addAll(varoVastustajaa);
        ylennaTietystaKategoriasta(apu, this.hyokkaystaJaVaromista);
        apu.clear();
        apu.addAll(varmaVoitto);
        apu.addAll(taytyyEstaa);
        ylennaTietystaKategoriasta(apu, this.getVoittamistaJaEstamista());
    }
    
    private void ylennaTietystaKategoriasta(ArrayList<Merkki> ylennettava, ArrayList<Merkki> mihinYlenee) {
        for (int i = 0; i < ylennettava.size()-1; i++) {
            boolean ylentyiko = false;
            for (int j = i+1; j < ylennettava.size(); j++) {
                if (samatKoordinaatit(ylennettava.get(i), ylennettava.get(j))) {
                    mihinYlenee.add(ylennettava.get(i));
                    ylennettava.remove(j);
                    ylennettava.remove(i);
                    ylentyiko = true;
                }
            }
            if (ylentyiko) {
                ylennaTietystaKategoriasta(ylennettava, mihinYlenee);
                break;
            }
        }
    }
    
    private boolean samatKoordinaatit(Merkki merkki1, Merkki merkki2) {
        if (merkki1.getX() == merkki2.getX() && merkki1.getY() == merkki2.getY()) {
            return true;
        }
        return false;
    }
    
    private void alustaLaatuJonot() {
        ArrayList<String> jonotMerkkeina = new ArrayList<String>();
        jonotMerkkeina.add("_");
        jonotMerkkeina.add("_x_");
        jonotMerkkeina.add("__x_");
        jonotMerkkeina.add("_xx_");
        jonotMerkkeina.add("__xx_");
        jonotMerkkeina.add("_xxx_");
        jonotMerkkeina.add("__xxx_");
        jonotMerkkeina.add("_xxxx_");
        jonotMerkkeina.add("_xo");
        jonotMerkkeina.add("_xxo");
        jonotMerkkeina.add("_xxxo");
        jonotMerkkeina.add("_xxxxo");
        jonotMerkkeina.add("__xo");
        jonotMerkkeina.add("__xxo");
        jonotMerkkeina.add("__xxxo");
        luoLaatuJonot(jonotMerkkeina);
    }
    
    private void luoLaatuJonot(ArrayList<String> merkkeina) {
        for (String string : merkkeina) {
            ArrayList<Laatu> yksiJonoRistille = new ArrayList<Laatu>();
            ArrayList<Laatu> yksiJonoNollalle = new ArrayList<Laatu>();
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '_') {
                    lisaaVali(yksiJonoRistille, yksiJonoNollalle);
                } else if (string.charAt(i) == 'x') {
                    lisaaRistiJaNolla(yksiJonoRistille, yksiJonoNollalle);
                } else if (string.charAt(i) == 'o') {
                    lisaaRistiJaNolla(yksiJonoNollalle, yksiJonoRistille);
                } else {
                    System.out.println("Virhe");
                }
            }
            this.puolikkaatJonotRistille.add(yksiJonoRistille);
            this.puolikkaatJonotNollalle.add(yksiJonoNollalle);
        }
    }
    
    private void nollaaListat() {
        this.ehkaHyodyllinen = new ArrayList<Merkki>();
        this.hairitseVastustajaa = new ArrayList<Merkki>();
        this.rakennaHyokkays = new ArrayList<Merkki>();
        this.taytyyEstaa = new ArrayList<Merkki>();
        this.varmaVoitto = new ArrayList<Merkki>();
        this.varoVastustajaa = new ArrayList<Merkki>();
        this.hyodyllistaJaHairintaa = new ArrayList<Merkki>();
        this.hyokkaystaJaVaromista = new ArrayList<Merkki>();
        this.voittamistaJaEstamista = new ArrayList<Merkki>();
    }

    private void lisaaRistiJaNolla(ArrayList<Laatu> risti, ArrayList<Laatu> nolla) {
        risti.add(Laatu.RISTI);
        nolla.add(Laatu.NOLLA);
    }

    private void lisaaVali(ArrayList<Laatu> risti, ArrayList<Laatu> nolla) {
        risti.add(Laatu.TYHJA);
        nolla.add(Laatu.TYHJA);
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin voisi olla hyödyllistä laittaa
     * @return the ehkaHyodyllinen
     */
    public ArrayList<Merkki> getEhkaHyodyllinen() {
        return ehkaHyodyllinen;
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin laittamalla saadaan kenties
     * hyökkäys aikaiseksi
     * @return the rakennaHyokkays
     */
    public ArrayList<Merkki> getRakennaHyokkays() {
        return rakennaHyokkays;
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin laittamalla pitäisi tulla varma voitto
     * @return the varmaVoitto
     */
    public ArrayList<Merkki> getVarmaVoitto() {
        return varmaVoitto;
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin laittamalla vastustaja saattaa
     * häiriintyä
     * @return the hairitseVastustajaa
     */
    public ArrayList<Merkki> getHairitseVastustajaa() {
        return hairitseVastustajaa;
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin laittamalla estetään rakennettavat
     * hyökkäykset
     * @return the varoVastustajaa
     */
    public ArrayList<Merkki> getVaroVastustajaa() {
        return varoVastustajaa;
    }

    /**
     * Vihjelista merkeistä, joiden koordinaatteihin laittamalla estetään mahdollisesti
     * vastustajaa saamasta varmaa voittoa
     * @return the taytyyEstaa
     */
    public ArrayList<Merkki> getTaytyyEstaa() {
        return taytyyEstaa;
    }

    /**
     * Vihjelista merkeistä, joista itse hyötyy ja samalla häiritsee vastustajaa
     * @return the hyodyllistaJaHairintaa
     */
    public ArrayList<Merkki> getHyodyllistaJaHairintaa() {
        return hyodyllistaJaHairintaa;
    }

    /**
     * Vihjelista merkeistä, joiden avulla itse hyökkää ja varoo samalla vastustajaa
     * @return the hyokkaystaJaVaromista
     */
    public ArrayList<Merkki> getHyokkaystaJaVaromista() {
        return hyokkaystaJaVaromista;
    }

    /**
     * Vihjelista merkeistä, joiden avulla kenties itse voittaa ja estää vastustajaa voittamasta
     * @return the voittamistaJaEstamista
     */
    public ArrayList<Merkki> getVoittamistaJaEstamista() {
        return voittamistaJaEstamista;
    }
}
