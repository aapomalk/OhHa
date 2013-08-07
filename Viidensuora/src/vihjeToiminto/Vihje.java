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
import viidensuora.Merkki;
import viidensuora.Laatu;
import viidensuora.MerkkienJononLoytaja;

public class Vihje {

    private TyhjienMerkkienLisaaja taydentaja;
    private static final int puoletJonoista = 9;
    private ArrayList<Merkki> rakennaHyokkays;
    private ArrayList<Merkki> varmaVoitto;
    private ArrayList<Merkki> varoVastustajaa;
    private ArrayList<Merkki> taytyyEstaa;
    private ArrayList<MerkkienJononLoytaja> etsijat;

    public Vihje() {
        etsijat = new ArrayList<MerkkienJononLoytaja>();
        ArrayList<ArrayList<Laatu>> laatuJonot;
        taydentaja = new TyhjienMerkkienLisaaja();
        laatuJonot = new ArrayList<ArrayList<Laatu>>();
        alustaLaatuJonot(laatuJonot);
        nollaaListat();
        syotaJonotEtsijoille(laatuJonot);
    }
    
    private void syotaJonotEtsijoille(ArrayList<ArrayList<Laatu>> laatuJonot) {
        for (ArrayList<Laatu> arrayList : laatuJonot) {
            etsijat.add(new MerkkienJononLoytaja(arrayList));
        }
    }
    
    private void nollaaListat() {
        this.rakennaHyokkays = new ArrayList<Merkki>();
        this.varmaVoitto = new ArrayList<Merkki>();
        this.varoVastustajaa = new ArrayList<Merkki>();
        this.taytyyEstaa = new ArrayList<Merkki>();
    }
    
    public void etsiRistilleVihjeet(ArrayList<Merkki> merkit) {
        etsiVihjeet(merkit, true);
    }
    
    public void etsiNollalleVihjeet(ArrayList<Merkki> merkit) {
        etsiVihjeet(merkit, false);
    }
    
    private void etsiVihjeet(ArrayList<Merkki> merkit, boolean ristille) {
        this.taydentaja.lisaaTyhjatMerkit(merkit);
        for (int i = 0; i < puoletJonoista; i++) {
            if (i < 6 && ristille) {
                this.varoVastustajaa.addAll(etsijat.get(i+puoletJonoista).tarkasta(merkit));
                this.rakennaHyokkays.addAll(etsijat.get(i).tarkasta(merkit));
            }
            if (i < 6 && !ristille) {
                this.rakennaHyokkays.addAll(etsijat.get(i+puoletJonoista).tarkasta(merkit));
                this.varoVastustajaa.addAll(etsijat.get(i).tarkasta(merkit));
            }
            if (i >= 6 && i < 9 && ristille) {
                this.taytyyEstaa.addAll(etsijat.get(i+puoletJonoista).tarkasta(merkit));
                this.varmaVoitto.addAll(etsijat.get(i).tarkasta(merkit));
            }
            if (i >= 6 && i < 9 && !ristille) {
                this.varmaVoitto.addAll(etsijat.get(i+puoletJonoista).tarkasta(merkit));
                this.taytyyEstaa.addAll(etsijat.get(i).tarkasta(merkit));
            }
        }
    }
    

    private void alustaLaatuJonot(ArrayList<ArrayList<Laatu>> laatuJonot) {
        for (int i = 0; i < puoletJonoista * 2; i++) {
            laatuJonot.add(new ArrayList<Laatu>());
        }
        jaaRakentaminenMetodeihin(laatuJonot);
    }

    private void jaaRakentaminenMetodeihin(ArrayList<ArrayList<Laatu>> laatuJonot) {
        kaksiJaKolmeVierekkain(laatuJonot);
        kolmeValinKanssa(laatuJonot);
        kolmeIsonValinKanssa(laatuJonot);
        neljaJaTyhja(laatuJonot);
    }

    private void kaksiJaKolmeVierekkain(ArrayList<ArrayList<Laatu>> laatuJonot) {
        
        int risti1 = 0;
        int risti2 = 1;
        int risti3 = 2;
        int nolla1 = risti1 + puoletJonoista;
        int nolla2 = risti2 + puoletJonoista;
        int nolla3 = risti3 + puoletJonoista;
        lisaaVali(laatuJonot.get(risti1), laatuJonot.get(nolla1));
        lisaaVali(laatuJonot.get(risti2), laatuJonot.get(nolla2));
        lisaaVali(laatuJonot.get(risti3), laatuJonot.get(nolla3));

        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                lisaaVali(laatuJonot.get(risti1), laatuJonot.get(nolla1));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti1), laatuJonot.get(nolla1));
            }
            if (i == 1) {
                lisaaVali(laatuJonot.get(risti2), laatuJonot.get(nolla2));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti2), laatuJonot.get(nolla2));
            }
            lisaaRistiJaNolla(laatuJonot.get(risti3), laatuJonot.get(nolla3));
        }
    }

    private void kolmeValinKanssa(ArrayList<ArrayList<Laatu>> laatuJonot) {
        
        int risti4 = 3;
        int risti5 = 4;
        
        int nolla4 = risti4 + puoletJonoista;
        int nolla5 = risti5 + puoletJonoista;
        
        lisaaVali(laatuJonot.get(risti4), laatuJonot.get(nolla4));
        lisaaVali(laatuJonot.get(risti5), laatuJonot.get(nolla5));
        
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                lisaaVali(laatuJonot.get(risti4), laatuJonot.get(nolla4));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti4), laatuJonot.get(nolla4));
            }
            if (i == 1) {
                lisaaVali(laatuJonot.get(risti5), laatuJonot.get(nolla5));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti5), laatuJonot.get(nolla5));
            }
        }
    }

    private void kolmeIsonValinKanssa(ArrayList<ArrayList<Laatu>> laatuJonot) {
        
        int risti6 = 5;
        
        int nolla6 = risti6 + puoletJonoista;
        for (int i = 0; i < 5; i++) {
            if (i == 1 || i == 2) {
                lisaaVali(laatuJonot.get(risti6), laatuJonot.get(nolla6));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti6), laatuJonot.get(nolla6));
            }
        }
    }

    private void neljaJaTyhja(ArrayList<ArrayList<Laatu>> laatuJonot) {
        
        int risti7 = 6;
        int risti8 = 7;
        int risti9 = 8;
        int nolla7 = risti7 + puoletJonoista;
        int nolla8 = risti8 + puoletJonoista;
        int nolla9 = risti9 + puoletJonoista;
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                lisaaVali(laatuJonot.get(risti7), laatuJonot.get(nolla7));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti7), laatuJonot.get(nolla7));
            }
            if (i == 1) {
                lisaaVali(laatuJonot.get(risti8), laatuJonot.get(nolla8));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti8), laatuJonot.get(nolla8));
            }
            if (i == 0) {
                lisaaVali(laatuJonot.get(risti9), laatuJonot.get(nolla9));
            } else {
                lisaaRistiJaNolla(laatuJonot.get(risti9), laatuJonot.get(nolla9));
            }
        }
    }

    private void lisaaRistiJaNolla(ArrayList<Laatu> risti, ArrayList<Laatu> nolla) {
        risti.add(Laatu.RISTI);
        nolla.add(Laatu.NOLLA);
    }

    private void lisaaVali(ArrayList<Laatu> risti, ArrayList<Laatu> nolla) {
        risti.add(Laatu.TYHJA);
        nolla.add(Laatu.TYHJA);
    }
}
