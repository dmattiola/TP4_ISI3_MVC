package model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import static model.Service.decodeColor;

public class TortueAmelioree extends Tortue{
    
    private String nom;
    private ArrayList<Tortue> tortuesConnues;
    
    public TortueAmelioree(){
        super();
        this.nom = "Franklin";
        this.tortuesConnues = new ArrayList<>();
    }
    
    public TortueAmelioree(String nom){
        super();
        this.nom = nom;
        this.tortuesConnues = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public double distance(Tortue t){
        double xx = (t.getX()-super.getX()) * (t.getX()-super.getX());
        double yy = (t.getY()-super.getY()) * (t.getY()-super.getY());
        return (Math.sqrt(xx+yy));
    }
    
    public void addTortueConnue(Tortue t){
        this.tortuesConnues.add(t);
    }
    
    public void removeTortueConnue(Tortue t){
        this.tortuesConnues.remove(t);
    }
    
    public void deplacement(){
        for (int i=0;i<this.tortuesConnues.size();i++){
            if(distance(this.tortuesConnues.get(i))<15){
                System.out.println("BONJOUR Tortue "+((TortueAmelioree)this.tortuesConnues.get(i)).getNom());
                this.tortuesConnues.get(i).droite(45);
                this.tortuesConnues.get(i).avancer(50);
                // a vérifier
            }
        }
    }
    
    @Override
    public void drawTurtle(Graphics graph) {
        if (graph==null)
                return;

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(this.getX(),this.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta=Tortue.getRatioDegRad()*(-this.getDir());
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
        graph.setColor(decodeColor(this.getColor()));
        graph.fillPolygon(arrow);
        
        // on ajoute le nom pour les tortues améliorées
        graph.drawString(this.getNom(),this.getX()+6,this.getY());
    }
}
