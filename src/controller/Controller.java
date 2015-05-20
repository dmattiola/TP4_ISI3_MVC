package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import javax.swing.JComboBox;
import view.FeuilleDessin;
import view.SimpleLogo;
import model.Tortue;
import model.TortueAmelioree;


public class Controller implements ActionListener,MouseListener, ItemListener {
    
    private SimpleLogo s;
    private FeuilleDessin f;
    
    public Controller(){
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // actions des boutons du haut
	if (action.equals("Avancer")) {
                System.out.println("command avancer");
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
		/*else if (action.equals("Lever")){
			this.f.getT_courante().leverCrayon();
                } 
                else if (action.equals("Baisser")){
			this.f.getT_courante().baisserCrayon();
		// actions des boutons du bas
                }*/
                else if (action.equals("Ajouter")){
                    f.addTortue(new TortueAmelioree());
                }
                else if (action.equals("Proc1"))
			this.f.getT_courante().proc1();
		else if (action.equals("Proc2"))
			this.f.getT_courante().proc2();
		else if (action.equals("Proc3"))
			this.f.getT_courante().proc3();
		else if (action.equals("Effacer"))
			s.effacer();
		else if (action.equals("Quitter"))
			s.quitter();
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
