package logoInit;

import controller.Controller;
import model.Tortue;
import model.TortueAmelioree;
import view.SimpleLogo;

public class Main {
    public static void main(String[] args) {
    //Instanciation de notre modèle
        TortueAmelioree t = new TortueAmelioree();
    //Création du contrôleur
        Controller controler = new Controller();
    //Création de notre fenêtre avec le contrôleur en paramètre
        SimpleLogo sl = new SimpleLogo(controler,t);
    //Ajout de la fenêtre comme observer de notre modèle
        
    }
}
