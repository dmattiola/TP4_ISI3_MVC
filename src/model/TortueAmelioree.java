package model;

import java.awt.*;
import java.util.ArrayList;
import static model.Service.decodeColor;

public class TortueAmelioree extends Tortue{
    
    // ATTRIBUTS
    private String nom;
    private ArrayList<Tortue> tortuesConnues;
    
    // CONSTRUCTEURS
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
    
    public TortueAmelioree(String nom, int x, int y, int n){
        super(n,x,y);
        this.nom = nom;
        this.tortuesConnues = new ArrayList<>();
    }

    // METHODES
    public double distance(Tortue t){
        double xx = (t.getX()-super.getX()) * (t.getX()-super.getX());
        double yy = (t.getY()-super.getY()) * (t.getY()-super.getY());
        return (Math.sqrt(xx+yy));
    }
    
    public void addTortueConnue(Tortue t){
        this.getTortuesConnues().add(t);
    }
    
    public void removeTortueConnue(Tortue t){
        this.getTortuesConnues().remove(t);
    }
    
    public synchronized Tortue tortueProche(int dist){
        for (int i=0;i<this.getTortuesConnues().size();i++){
            if(distance(this.getTortuesConnues().get(i))<dist){
                return this.getTortuesConnues().get(i);
            }
        }
        return null;
    }
    
    public void deplacement(){
        for (int i=0;i<this.getTortuesConnues().size();i++){
            if(distance(this.getTortuesConnues().get(i))<15){
                System.out.println("BONJOUR Tortue "+((TortueAmelioree)this.getTortuesConnues().get(i)).getNom());
                this.getTortuesConnues().get(i).droite(45);
                this.getTortuesConnues().get(i).avancer(50);
                // a vérifier
            }
        }
    }
    
    public Tortue tortueLaPlusProche(Tortue t){
        Tortue proche = null;
        double distance = Double.MAX_VALUE;
        for (int i=0;i<this.tortuesConnues.size();i++){
            double di = distance(this.tortuesConnues.get(i));
            if (di < distance){
                proche = this.tortuesConnues.get(i);
                distance = di;
            }
        }
        return proche;
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
    
    // GETTERS & SETTERS
    public String getNom(){ return nom; }
    public void setNom(String nom){ this.nom = nom; }
    public ArrayList<Tortue> getTortuesConnues(){ return tortuesConnues; }
    public void setTortuesConnues(ArrayList<Tortue> tortuesConnues) { this.tortuesConnues = tortuesConnues; }

}
