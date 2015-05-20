/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author Dylan
 */
public class TortueAmelioree extends Tortue{
    
    private String nom;
    private ArrayList<TortueAmelioree> tortuesConnues;
    
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
    
    public double distance(TortueAmelioree t){
        double xx = (t.getX()-super.getX()) * (t.getX()-super.getX());
        double yy = (t.getY()-super.getY()) * (t.getY()-super.getY());
        return (Math.sqrt(xx+yy));
    }
    
    public void addTortueConnue(TortueAmelioree t){
        this.tortuesConnues.add(t);
    }
    
    public void removeTortueConnue(TortueAmelioree t){
        for(int i=0;i<this.tortuesConnues.size();i++){
            if (this.tortuesConnues.get(i)==t){
                this.tortuesConnues.remove(i);
            }
        }
    }
    
    public void deplacement(){
        for (int i=0;i<this.tortuesConnues.size();i++){
            if(distance(this.tortuesConnues.get(i))<15){
                System.out.println("BONJOUR Tortue "+this.tortuesConnues.get(i).getNom());
            }
        }
    }
}
