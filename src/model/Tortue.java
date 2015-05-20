package model;

import java.awt.Graphics;
import java.util.*;


/** La classe Tortue qui se deplace en coordonnees polaires
 * 
 * Modele(tortue) : aucune methode de dessin
 * Controleur séparé de la vue actionPerformed
 * Enelver le repaint en fin de Actionperformed
 * a la place utilsier le pattern observer observable entre modele et vue
**/

public abstract class Tortue extends Observable{

    // ATTRIBUTS	
    private static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
    private static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    private int x,y;	
    private int dir;// Direction de la tortue (angle en degres)
    protected int coul;

    // CONSTRUCTEURS
    public Tortue() {
        this.setPosition(500/2, 400/2);
        this.dir = -90;
        this.setColor(0);
    }

    public Tortue(int n) {
        this.setPosition(500/2, 400/2);
        this.dir = -90;
        this.setColor(n);
    }
    
    public Tortue(int n, int x, int y) {
        this.setPosition(x, y);
        this.dir = -90;
        this.setColor(n);
    }
    
    // METHODES
    public void reset() {
        // on initialise la position de la tortue
        this.x = 0;
        this.y = 0;
        this.setDir(-90);
        this.setColor(0);
    }
   
    // avancer de n pas
    public void avancer(int dist) {
        int newX = (int) Math.round(this.getX()+dist*Math.cos(getRatioDegRad()*this.getDir()));
        int newY = (int) Math.round(this.getY()+dist*Math.sin(getRatioDegRad()*this.getDir()));
        if (newX <= 0+5 || newY <=0+5 || newX >= 600-5 || newY >= 400-5){
            //System.out.println("Tortue sort du plateau, Déplacement non autorisé !");
        }
        else{
            this.setPosition(newX,newY);
        }
        this.setChanged();
        this.notifyObservers();
    }

    // aller a droite
    public void droite(int ang) {
        this.setDir((this.getDir() + ang) % 360);
        this.setChanged();
        this.notifyObservers();
    }

    // aller a gauche
    public void gauche(int ang){
        this.setDir((this.getDir() - ang) % 360);
        this.setChanged();
        this.notifyObservers();
    }

    //mouvement aléatoire
    public void moveRandom(int val){
        Random rand = new Random();
        int v = rand.nextInt(999);
        if (v >= 0 && v <= 500){
            avancer(val);
        } else if (v >= 501 && v <= 750){
            droite(45);
        } else {
            gauche(45);
        }
        this.setChanged();
        this.notifyObservers();
    }
    public abstract void drawTurtle(Graphics graph);

    // GETTERS & SETTERS
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getDir(){ return dir; }
    public static double getRatioDegRad(){ return ratioDegRad; }
    public static int getRp(){ return rp; }
    public static int getRb(){ return rb; }
    public int getColor(){ return coul; }
    public void setColor(int n){
        coul = n;
        this.setChanged();
        this.notifyObservers();
    }
    public void setPosition(int newX, int newY){
        this.x = newX;
        this.y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
    
}
