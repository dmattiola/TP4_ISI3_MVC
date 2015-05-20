package view;

import model.Tortue;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class FeuilleDessin extends JPanel implements Observer{
    
	private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
        private Tortue t_courante;
        private Tortue t_balle;
        private int couleur_courante = 0;
        private int width,height;
        
	public FeuilleDessin() {
		tortues = new ArrayList<Tortue>();
	}

	public void addTortue(Tortue o) {
                o.setColor(getCouleur_courante());
                this.getTortues().add(o);
                setT_courante(o);
                getT_courante().addObserver(this);
                update(o,this);
	}

        public void addBalle(Tortue o){
            o.addObserver(this);
            update(o,this);
        }
	public void reset() {
		for (Iterator it = getTortues().iterator();it.hasNext();) {
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
		for(Iterator it = getTortues().iterator();it.hasNext();) {
                    Tortue t = (Tortue) it.next();
                    setT_courante(t);
                    t.drawTurtle(g);
		}
            setT_courante(temp);
	}
        
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTortues(ArrayList<Tortue> tortues) {
        this.tortues = tortues;
    }

    public Tortue getT_balle() {
        return t_balle;
    }

    public void setT_balle(Tortue t_balle) {
        this.t_balle = t_balle;
    }

}
