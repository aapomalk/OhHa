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
    private ArrayList<Merkki> rakennaHyokkays;
    private ArrayList<Merkki> varmaVoitto;
    private ArrayList<Merkki> varoVastustajaa;
    private ArrayList<Merkki> taytyyEstaa;
    private MerkkienJononLoytaja etsija;
    private ArrayList<Laatu> puolikkaatJonot;

    public Vihje() {
        etsija = new MerkkienJononLoytaja();
        ArrayList<ArrayList<Laatu>> laatuJonot;
        taydentaja = new TyhjienMerkkienLisaaja();
        laatuJonot = new ArrayList<ArrayList<Laatu>>();
        alustaLaatuJonot(laatuJonot);
        nollaaListat();
    }
    
    private void alustaLaatuJonot(ArrayList<ArrayList<Laatu>> laatuJonot) {
        
    }
    
    private void nollaaListat() {
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
}
