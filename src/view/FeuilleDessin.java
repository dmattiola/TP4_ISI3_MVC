package view;

import controller.Controller;
import model.Tortue;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import model.Segment;
import static model.Service.decodeColor;

public class FeuilleDessin extends JPanel implements Observer{
    
	private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
        private Tortue t_courante;
        private int couleur_courante = 0;
        
	public FeuilleDessin() {
		tortues = new ArrayList<Tortue>();
	}

	public void addTortue(Tortue o) {
                o.setColor(getCouleur_courante());
		this.tortues.add(o);
                setT_courante(o);
                getT_courante().addObserver(this);
                update(o,this);
	}

	public void reset() {
		for (Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			t.reset();
		}
	}

        @Override
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
            Tortue temp = getT_courante();
		for(Iterator it = tortues.iterator();it.hasNext();) {
                    Tortue t = (Tortue) it.next();
                    setT_courante(t);
                    t.drawTurtle(g);
		}
            setT_courante(temp);
	}
        
       /* 
        public void drawTurtle (Graphics graph) {
		if (graph==null)
			return;
		
		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(this.t_courante.getX(),this.t_courante.getY());
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=Tortue.getRatioDegRad()*(-this.t_courante.getDir());
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
        graph.setColor(decodeColor(this.getT_courante().getColor()));
        graph.fillPolygon(arrow);
    }
    */
        
    @Override
    public void update(Observable o, Object o1) {
        this.repaint();
   }

    public Tortue getT_courante() {
        return t_courante;
    }

    public void setT_courante(Tortue t_courante) {
        this.t_courante = t_courante;
    }
    
    public ArrayList<Tortue> getTortues(){
        return tortues;
    }

    public int getCouleur_courante() {
        return couleur_courante;
    }

    public void setCouleur_courante(int couleur_courante) {
        this.couleur_courante = couleur_courante;
    }

}
