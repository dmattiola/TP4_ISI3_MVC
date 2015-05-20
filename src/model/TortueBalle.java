package model;

import java.awt.Graphics;
import static model.Service.decodeColor;

public class TortueBalle extends Tortue{
    
    private Tortue possesseur;
    
    public TortueBalle(){
        super();
    }
    
    @Override
    public void drawTurtle(Graphics graph){
        graph.setColor(decodeColor(this.coul));
        graph.fillOval(possesseur.getX()-5,possesseur.getY()-10,10,10);
    }
}
