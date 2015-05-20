package model;

import java.awt.Graphics;
import java.util.*;
import view.FeuilleDessin;


/** La classe Tortue qui se deplace en coordonnees polaires
 * 
 * Modele(tortue) : aucune methode de dessin
 * Controleur séparé de la vue actionPerformed
 * Enelver le repaint en fin de Actionperformed
 * a la place utilsier le pattern observer observable entre modele et vue
 * 
 * 
**/

public abstract class Tortue extends Observable{

    // Attributs statiques	
    private static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
    private static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    public static double getRatioDegRad() {
        return ratioDegRad;
    }

    public static int getRp() {
        return rp;
    }

    public static int getRb() {
        return rb;
    }

    // Attributs
    private int x,y;	

    private int dir;// Direction de la tortue (angle en degres)
    private boolean crayon;// par defaut on suppose qu'on dessine
    protected int coul;

    // Methodes
    public void setColor(int n) {
        coul = n;
        this.setChanged();
        this.notifyObservers();
    }
    public int getColor() {
        return coul;
    }

    public Tortue() {
        this.setPosition(500/2, 400/2);
        this.dir = -90;
        this.setColor(0);
        this.crayon = true;
    }

    public Tortue(int n) {
        this.setPosition(500/2, 400/2);
        this.dir = -90;
        this.setColor(n);
        this.crayon = true;
    }
    
    public Tortue(int n, int x, int y) {
        this.setPosition(x, y);
        this.dir = -90;
        this.setColor(n);
        this.crayon = true;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    /** quelques classiques */
    public void reset() {
        // on initialise la position de la tortue
        this.x = 0;
        this.y = 0;
        this.dir = -90;
        this.setColor(0);
        this.crayon = true;
    }
    
    /** les procedures de base de fonctionnement de la tortue */
    
    // avancer de n pas
    public void avancer(int dist) {
        int newX = (int) Math.round(this.getX()+dist*Math.cos(getRatioDegRad()*this.getDir()));
        int newY = (int) Math.round(this.getY()+dist*Math.sin(getRatioDegRad()*this.getDir()));
        if (newX <= 0 || newY <=0 || newX >= 600 || newY >= 400){
            System.out.println("Tortue sort du plateau, Déplacement non autorisé !");
        }
        else{
            this.setPosition(newX,newY);
        }
        this.setChanged();
        this.notifyObservers();
    }

    // aller a droite
    public void droite(int ang) {
            this.dir = (this.getDir() + ang) % 360;
            this.setChanged();
            this.notifyObservers();
    }

    // aller a gauche
    public void gauche(int ang) {
            this.dir = (this.getDir() - ang) % 360;
            this.setChanged();
            this.notifyObservers();
    }


    // pour changer de couleur de dessin
    public void couleur(int n) {
            this.setColor(n % 12);
            this.setChanged();
            this.notifyObservers();
    }

    public void couleurSuivante() {
            couleur(this.getColor()+1);
            this.setChanged();
            this.notifyObservers();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

    public abstract void drawTurtle(Graphics graph);
}
