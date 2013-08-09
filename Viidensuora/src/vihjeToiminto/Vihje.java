/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vihjeToiminto;

/**
 *
 * @author aapomalk
 */
import java.util.ArrayList;
import viidensuora.Laatu;
import viidensuora.Merkki;
import viidensuora.MerkkienJononLoytaja;

public class Vihje {

    private TyhjienMerkkienLisaaja taydentaja;
    private ArrayList<Merkki> ehkaHyodyllinen;
    private ArrayList<Merkki> rakennaHyokkays;
    private ArrayList<Merkki> varmaVoitto;
    private ArrayList<Merkki> hairitseVastustajaa;
    private ArrayList<Merkki> varoVastustajaa;
    private ArrayList<Merkki> taytyyEstaa;
    private MerkkienJononLoytaja etsija;
    private ArrayList<ArrayList<Laatu>> puolikkaatJonotRistille;
    private ArrayList<ArrayList<Laatu>> puolikkaatJonotNollalle;
    private ArrayList<Integer> pisteytyslista;

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
        
        pisteytyslista.add(0);
        pisteytyslista.add(1);
        pisteytyslista.add(1);
        pisteytyslista.add(2);
        pisteytyslista.add(2);
        pisteytyslista.add(3);
        pisteytyslista.add(3);
        pisteytyslista.add(4);
        pisteytyslista.add(0);
        pisteytyslista.add(1);
        pisteytyslista.add(2);
        pisteytyslista.add(3);
        pisteytyslista.add(-1);
        pisteytyslista.add(0);
        pisteytyslista.add(2);
    }
    
    public void lisaaVihjeetListoihin(ArrayList<Merkki> merkit, Laatu laatu) {
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
        ArrayList<Merkki> koodiSininen1;
        ArrayList<Merkki> koodiSininen2;
        ArrayList<Merkki> koodiTurkoosi;
        ArrayList<Merkki> koodiVioletti;
        ArrayList<Merkki> koodiPunainen;
        ArrayList<Merkki> koodiVihrea;
        if (onkoRistille) {
            koodiSininen1 = this.getEhkaHyodyllinen();
            koodiSininen2 = this.getHairitseVastustajaa();
            koodiTurkoosi = this.getRakennaHyokkays();
            koodiVioletti = this.getVaroVastustajaa();
            koodiPunainen = this.getTaytyyEstaa();
            koodiVihrea = this.getVarmaVoitto();
        } else {
            koodiSininen2 = this.getEhkaHyodyllinen();
            koodiSininen1 = this.getHairitseVastustajaa();
            koodiVioletti = this.getRakennaHyokkays();
            koodiTurkoosi = this.getVaroVastustajaa();
            koodiVihrea = this.getTaytyyEstaa();
            koodiPunainen = this.getVarmaVoitto();
        }
        
        for (int i = 0; i < puolikkaatJonotRistille.size(); i++) {
            for (int j = i; j < puolikkaatJonotNollalle.size(); j++) {
                int arvo = this.pisteytyslista.get(i) + this.pisteytyslista.get(j);
                if (arvo > 0) {
                    boolean risti = this.etsija.tarkastaMerkki(merkit, merkki, puolikkaatJonotRistille.get(i), puolikkaatJonotRistille.get(j));
                    boolean nolla = this.etsija.tarkastaMerkki(merkit, merkki, puolikkaatJonotNollalle.get(i), puolikkaatJonotNollalle.get(j));
                    if (arvo == 1) {
                        if (risti) {
                            koodiSininen1.add(merkki);
                        }
                        if (nolla) {
                            koodiSininen2.add(merkki);
                        }
                    } else if (arvo == 2) {
                        if (risti) {
                            koodiTurkoosi.add(merkki);
                        }
                        if (nolla) {
                            koodiVioletti.add(merkki);
                        }
                    } else if (arvo > 2) {
                        if (risti) {
                            koodiVihrea.add(merkki);
                        }
                        if (nolla) {
                            koodiPunainen.add(merkki);
                        }
                    }
                }
            }
        }
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
     * @return the ehkaHyodyllinen
     */
    public ArrayList<Merkki> getEhkaHyodyllinen() {
        return ehkaHyodyllinen;
    }

    /**
     * @return the rakennaHyokkays
     */
    public ArrayList<Merkki> getRakennaHyokkays() {
        return rakennaHyokkays;
    }

    /**
     * @return the varmaVoitto
     */
    public ArrayList<Merkki> getVarmaVoitto() {
        return varmaVoitto;
    }

    /**
     * @return the hairitseVastustajaa
     */
    public ArrayList<Merkki> getHairitseVastustajaa() {
        return hairitseVastustajaa;
    }

    /**
     * @return the varoVastustajaa
     */
    public ArrayList<Merkki> getVaroVastustajaa() {
        return varoVastustajaa;
    }

    /**
     * @return the taytyyEstaa
     */
    public ArrayList<Merkki> getTaytyyEstaa() {
        return taytyyEstaa;
    }
}
