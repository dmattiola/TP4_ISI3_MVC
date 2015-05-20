/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Graphics;
import static model.Service.decodeColor;

/**
 *
 * @author Dylan
 */
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
