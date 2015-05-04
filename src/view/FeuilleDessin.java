package view;

import controller.Controller;
import model.Tortue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import model.Segment;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel implements Observer{
    
	private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
	private Controller c;
        
	public FeuilleDessin(Controller c) {
		tortues = new ArrayList<Tortue>();
                this.c=c;
	}

	public void addTortue(Tortue o) {
		tortues.add(o);
	}

	public void reset() {
		for (Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			t.reset();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);

		showTurtles(g);
	}
	
	public void showTurtles(Graphics g) {
		for(Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			this.drawTurtle(g);
		}
	}
        
        public void drawTurtle (Graphics graph) {
		if (graph==null)
			return;
		
		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(c.getT_courante().getX(),c.getT_courante().getY());
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=Tortue.getRatioDegRad()*(-c.getT_courante().getDir());
		//Demi angle au sommet du triangle
		double alpha=Math.atan( (float)Tortue.getRb() / (float)Tortue.getRp() );
		//Rayon de la fleche
		double r=Math.sqrt( Tortue.getRp()*Tortue.getRp() + Tortue.getRb()*Tortue.getRb() );
		//Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)), (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),(int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ), (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graph.setColor(Color.green);
        graph.fillPolygon(arrow);
    }
    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
   }
}
