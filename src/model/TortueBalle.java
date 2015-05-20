package model;

import java.awt.Graphics;
import static model.Service.decodeColor;

public class TortueBalle extends Tortue{
    
    // ATTRIBUTS
    private Tortue possesseur;
    
    // CONSTRUCTEURS
    public TortueBalle(){
        super();
    }
    
    public TortueBalle(Tortue t){
        super(7,t.getX(),t.getY());
        this.possesseur = t;
    }
    
    // METHODES
    public void versTortue(TortueAmelioree t){
        super.setPosition(t.getX(), t.getY());
        super.setDir(t.getDir());
        this.possesseur = t;
        this.setChanged();
        this.notifyObservers();
    }
    
    @Override
    public void drawTurtle(Graphics graph){
        graph.setColor(decodeColor(this.coul));
        graph.fillOval(possesseur.getX()-5,possesseur.getY(),10,10);
        this.setChanged();
        this.notifyObservers();
    }
    
}
