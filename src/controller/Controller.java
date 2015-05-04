package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.FeuilleDessin;
import view.SimpleLogo;
import model.Tortue;


public class Controller implements ActionListener {
    
    private Tortue t_courante;
    private SimpleLogo s;
    private FeuilleDessin f;
    
    public Controller(Tortue t){
        this.t_courante = t;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // actions des boutons du haut
	if (action.equals("Avancer")) {
                System.out.println("command avancer");
                try {
                        int v = Integer.parseInt(s.getInputValue());
                        this.getT_courante().avancer(v);
                } catch (NumberFormatException ex){
                        System.err.println("ce n'est pas un nombre : " + s.getInputValue());
                }
        }
		else if (action.equals("Droite")) {
			try {
				int v = Integer.parseInt(s.getInputValue());
				getT_courante().droite(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + s.getInputValue());
			}
		}
		else if (action.equals("Gauche")) {
			try {
				int v = Integer.parseInt(s.getInputValue());
				getT_courante().gauche(v);
			} catch (NumberFormatException ex){
				System.err.println("ce n'est pas un nombre : " + s.getInputValue());
			}
		}
		else if (action.equals("Lever")) 
			getT_courante().leverCrayon();
		else if (action.equals("Baisser"))
			getT_courante().baisserCrayon();
		// actions des boutons du bas
		else if (action.equals("Proc1"))
			getT_courante().proc1();
		else if (action.equals("Proc2"))
			getT_courante().proc2();
		else if (action.equals("Proc3"))
			getT_courante().proc3();
		else if (action.equals("Effacer"))
			s.effacer();
		else if (action.equals("Quitter"))
			s.quitter();
    }

    public Tortue getT_courante() {
        return t_courante;
    }

    public void setT_courante(Tortue t_courante) {
        this.t_courante = t_courante;
    }

    public void setSimpleLogo(SimpleLogo s) {
        this.s = s;
    }

    public void setFeuilleDessin(FeuilleDessin f) {
        this.f = f;
    }
    
    
    
    
}
