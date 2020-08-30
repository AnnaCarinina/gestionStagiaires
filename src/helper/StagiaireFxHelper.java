package helper;

import bean.Stagiaire;
import java.util.List;

import javafx.scene.control.TableView;
import util.DateUtil;

public class StagiaireFxHelper extends AbstractFxHelper<Stagiaire> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Prenom", "prenom"),
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Date Naissance", "dateNaissance"),
            new AbstractFxHelperItem("Stage", "stagee"),
            new AbstractFxHelperItem("Departement", "departement")
            };
    }

    public StagiaireFxHelper(TableView<Stagiaire> table, List<Stagiaire> list) {
        super(titres, table, list);
    }

    public StagiaireFxHelper(TableView<Stagiaire> table) {
        super(titres, table);
    }

}