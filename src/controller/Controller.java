package controller;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import model.JeuDeBalle;
import view.FeuilleDessin;
import view.SimpleLogo;
import model.Tortue;
import model.TortueAmelioree;


public class Controller implements ActionListener,MouseListener, ItemListener {
    
    private SimpleLogo s;
    private FeuilleDessin f;
    private JeuDeBalle game;
    private Thread th;
    
    public Controller(){
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // actions des boutons du haut
	if (action.equals("Avancer")) {
            try {
                int v = Integer.parseInt(s.getInputValue());
                this.f.getT_courante().avancer(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + s.getInputValue());
            }
        }
        else if (action.equals("Droite")) {
            try {
                int v = Integer.parseInt(s.getInputValue());
                this.f.getT_courante().droite(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + s.getInputValue());
            }
        }
        else if (action.equals("Gauche")) {
            try {
                int v = Integer.parseInt(s.getInputValue());
                this.f.getT_courante().gauche(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + s.getInputValue());
            }
        }
        else if (action.equals("Ajouter")){
            f.addTortue(new TortueAmelioree());
        }
        else if (action.equals("Demarrer")){
            f.setTortues(new ArrayList<Tortue>());
            game = new JeuDeBalle(8,f);
            game.addTortues(f);
            game.addBalle(f);
            game.addObserver(f);
            th = new Thread(game);
            th.start();
            JButton boutton_demarrer = (JButton)ae.getSource();
            boutton_demarrer.setEnabled(false);
        }
        else if (action.equals("Arreter")){
            th.stop();
            JButton boutton_arreter = (JButton)ae.getSource();
            boutton_arreter.setEnabled(false);
        }
	else if (action.equals("Effacer")){
            s.effacer();
        }
	else if (action.equals("Quitter")){
            s.quitter();
        }		
    }

    public void setSimpleLogo(SimpleLogo s) {
        this.s = s;
    }

    public void setFeuilleDessin(FeuilleDessin f) {
        this.f = f;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for(Iterator it = this.f.getTortues().iterator();it.hasNext();) {
            Tortue t = (Tortue) it.next();
            if (me.getX()>=t.getX()-t.getRp()/2 && me.getX()<=t.getX()+t.getRp()/2 && me.getY()<=t.getY() && me.getY()>=t.getY()-t.getRb() ){
                this.f.setT_courante(t);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void itemStateChanged(ItemEvent ie) {
        JComboBox cb = (JComboBox)ie.getSource();
        int n = cb.getSelectedIndex();
	this.f.getT_courante().setColor(n);
        this.f.setCouleur_courante(n);
    }
    
    
    
    
}
