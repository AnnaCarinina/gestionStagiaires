package helper;

import bean.Encadrant;
import java.util.List;

import javafx.scene.control.TableView;

public class EncadrantFxHelper extends AbstractFxHelper<Encadrant> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom d'utilisateur", "login"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Prénom", "prenom"),
            new AbstractFxHelperItem("Mail", "email"),
            new AbstractFxHelperItem("Adresse", "adresse"),
            new AbstractFxHelperItem("Téléphone", "telephone"),
            new AbstractFxHelperItem("Genre", "gender"),
            new AbstractFxHelperItem("Stage", "stagee"),
            new AbstractFxHelperItem("Departement", "departement"),
            
        };
    }

    public EncadrantFxHelper(TableView<Encadrant> table, List<Encadrant> list) {
        super(titres, table, list);
    }

    public EncadrantFxHelper(TableView<Encadrant> table) {
        super(titres, table);
    }

}