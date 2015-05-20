package model;

import java.util.ArrayList;
import java.util.Observable;

public class JeuDeBalle extends Observable {
    
    private ArrayList<Tortue> tortues;
    private Tortue tortue_courante;
    private Tortue balle;
    
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
    
}
