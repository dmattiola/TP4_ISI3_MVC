package model;

import java.util.*;
import java.util.logging.*;
import static model.Service.decodeColor;
import view.FeuilleDessin;

public class JeuDeBalle extends Observable implements Runnable{
    
    private ArrayList<Tortue> tortues;
    private Tortue tortue_possesseur;
    private Tortue ancien_possesseur;
    private TortueBalle balle;
    private boolean isRunning = false;
    private FeuilleDessin f;
    private int nb_tortues;
    
    public JeuDeBalle(int nb_tortues,FeuilleDessin f){
        this.f=f;
        this.nb_tortues=nb_tortues;
        Random rand = new Random();
        this.tortues = new ArrayList<>();
        for(int i=0;i<nb_tortues;i++){
            this.tortues.add(new TortueAmelioree("Tortue"+i,rand.nextInt(600),rand.nextInt(400),i));
        }
        for (int i =0;i<nb_tortues;i++){
            for (int j =0;j<nb_tortues;j++){
                if (j != i){
                    ((TortueAmelioree)this.tortues.get(i)).addTortueConnue(this.tortues.get(j));
                }
            }
        }
        tortue_possesseur = this.tortues.get(rand.nextInt(nb_tortues));
        this.balle = new TortueBalle(tortue_possesseur);   
    }
    
    public JeuDeBalle(){ }

   public void donneBalle(TortueAmelioree oldT, TortueAmelioree newT){
       ancien_possesseur = oldT;
       tortue_possesseur = newT;
        getBalle().versTortue(newT);
       this.setChanged();
        this.notifyObservers();
   }
   
   public void addBalle(FeuilleDessin f){
       f.addBalle(balle);
   }
   
   public void addTortues(FeuilleDessin f){
       f.addTortue(getBalle());
       for(int i=0;i<tortues.size();i++){
           f.addTortue(tortues.get(i));
       }
   }
   
   public void stopGame(){
       isRunning = false;
   }

    @Override
    public void run() {
        isRunning = true;
        int nb_essai = 0;
        while(isRunning){
            for (int i=0;i<tortues.size();i++){
                if(f.getT_courante() != tortues.get(i)){
                    tortues.get(i).moveRandom(25);
                }
                if (tortue_possesseur == tortues.get(i)){
                    Tortue t = ((TortueAmelioree)tortues.get(i)).tortueProche(50);
                   
                    if (t != null && t != ancien_possesseur){
                         System.out.println(decodeColor(t.getColor()));
                        donneBalle((TortueAmelioree)tortue_possesseur,(TortueAmelioree)t);
                      //  getBalle().versTortue((TortueAmelioree)tortues.get(i));
                        System.out.println("PAsse effectuée");
                        break;
                    }
                }
            }
            
            if (nb_essai == 5){
                Tortue t = ((TortueAmelioree)tortues.get(new Random().nextInt(nb_tortues))).tortueLaPlusProche(tortue_possesseur);
                donneBalle((TortueAmelioree)tortue_possesseur,(TortueAmelioree)t);
                //getBalle().versTortue((TortueAmelioree)tortues.get(tortues.indexOf(t)));
                nb_essai=0;
            }
            nb_essai++;
            System.out.println("possesseur de la balle : "+((TortueAmelioree)tortue_possesseur).getNom());
            try {
                Thread.sleep(1000);
                System.out.println("ess");
            } catch (InterruptedException ex) {
                Logger.getLogger(JeuDeBalle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public TortueBalle getBalle() {
        return balle;
    }

    public void setBalle(TortueBalle balle) {
        this.balle = balle;
    }
}
