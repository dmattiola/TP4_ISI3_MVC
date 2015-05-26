package model;

import java.util.*;
import java.util.logging.*;
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
       for(int i=0;i<getTortues().size();i++){
           
           this.f.getT_courante().setColor(getTortues().get(i).getColor());
        this.f.setCouleur_courante(getTortues().get(i).getColor());
           f.addTortue(getTortues().get(i));
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
            for (int i=0;i<getTortues().size();i++){
                if(f.getT_courante() != getTortues().get(i)){
                    getTortues().get(i).moveRandom(25);
                }
                if (tortue_possesseur == getTortues().get(i)){
                    Tortue t = ((TortueAmelioree)getTortues().get(i)).tortueProche(50);
                   
                    if (t != null && t != ancien_possesseur){
                        donneBalle((TortueAmelioree)tortue_possesseur,(TortueAmelioree)t);
                        break;
                    }
                }
            }
            
            if (nb_essai == 5){
                Tortue t = ((TortueAmelioree)getTortues().get(new Random().nextInt(nb_tortues))).tortueLaPlusProche(tortue_possesseur);
                donneBalle((TortueAmelioree)tortue_possesseur,(TortueAmelioree)t);
                nb_essai=0;
            }
            nb_essai++;
            try {
                Thread.sleep(1000);
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

    public ArrayList<Tortue> getTortues() {
        return tortues;
    }

    public void setTortues(ArrayList<Tortue> tortues) {
        this.tortues = tortues;
    }
}
