package model;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import view.FeuilleDessin;

public class JeuDeBalle extends Observable {
    
    private ArrayList<Tortue> tortues;
    private Tortue tortue_courante;
    private Tortue balle;
    private int nb_tortues = 6;
    
    public JeuDeBalle(){
        this.tortues = new ArrayList<>();
    }
    
    public JeuDeBalle(ArrayList<Tortue> liste){
        this.tortues = liste;
    }
    
    public JeuDeBalle(ArrayList<Tortue> liste, Tortue courante){
        this.tortues = liste;
        this.tortue_courante = courante;
    }
 
    public void createJeuDeBalle(){
        Random rand = new Random();
        for (int i=0;i<nb_tortues;i++){
            Tortue t = new TortueAmelioree("Tortue"+i);
            t.setPosition(rand.nextInt(FeuilleDessin.WIDTH), rand.nextInt(FeuilleDessin.HEIGHT));
            tortues.add(t);
        }
        this.tortue_courante=null;
        this.balle=null;
    }
}
