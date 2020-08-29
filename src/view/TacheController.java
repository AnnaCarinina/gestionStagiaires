/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Encadrant;
import bean.Stagee;
import bean.Tache;
import helper.StagiaireFxHelper;
import helper.TacheFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.TacheFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author LEILA
 */
public class TacheController implements Initializable {

    /**
     * Initializes the controller class.
     */

    
    @FXML
    private Button stage;
    
    @FXML
    private Button stagiaire;
    
        @FXML
    private Button retour;
    
    @FXML
    private Button seDeconnecter;
    
    @FXML 
    private Pane paneAdd;

    @FXML
    private TextField nom;

    @FXML
    private TextField avancement;

    @FXML
    private TextField importance;

    @FXML
    private ComboBox<Stagee> stageComboBox;
        @FXML
    private TableView tacheTableView = new TableView();
        
            @FXML
    private AnchorPane tacheEditAvanc;
    @FXML
    private Label tacheInfo;
    @FXML
    private TextField tacheAvanc;
    @FXML
    private Button editAvancButton;
    



    TacheFxHelper tacheFxHelper;
    TacheFacade tacheFacade = new TacheFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageeFacade = new StageeFacade();
     EncadrantFacade encadrantFacade = new EncadrantFacade();
     
        Encadrant encadrant = (Encadrant) Session.getAttribut("encaConnect");


  
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     initHelper();
 
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        
        if(event.getSource().equals(stagiaire)){
          Acceuil.forward(event, "Stagiaire.fxml", this.getClass());
        }
             if(event.getSource().equals(retour)){
          Acceuil.forward(event, "EditeProfile.fxml", this.getClass());
        }
        
        if(event.getSource().equals(stage)){
            Acceuil.forward(event, "StageView.fxml", this.getClass());
        }
          
           if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
    }
    
        @FXML
    public void geteditAvanc(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            tacheTableView.setVisible(false);
            editAvancButton.setDisable(true);
            tacheEditAvanc.setVisible(true);
            tacheInfo.setText(tache.getNom() + " Avanc.");
            tacheAvanc.setText(tache.getAvancement().toString());
        }
    }

    @FXML
    public void editAvanc(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        tache.setAvancement(new Double(tacheAvanc.getText()));
        tacheFacade.edit(tache);
        stageeFacade.calculeAvanc(this.encadrant.getStagee());

        tacheEditAvanc.setVisible(false);
        editAvancButton.setDisable(false);
        tacheTableView.setVisible(true);
        tacheFxHelper.getTable().refresh();
        tacheFxHelper.setList(tacheFacade.findByStage(this.encadrant.getStagee()));
        
    }
    
    
    @FXML
    public void setComplet(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        tache.setAvancement(100D);
        if (tacheEditAvanc.isVisible()) {
            tacheEditAvanc.setVisible(false);
            editAvancButton.setDisable(false);
            tacheTableView.setVisible(true);
        }
        tacheFacade.edit(tache);
        stageeFacade.calculeAvanc(this.encadrant.getStagee());
        tacheFxHelper.getTable().refresh();
        tacheFxHelper.setList(tacheFacade.findByStage(this.encadrant.getStagee()));

    }
        private void initHelper() {
        tacheFxHelper = new TacheFxHelper(tacheTableView, tacheFacade.findByStage(this.encadrant.getStagee()));
    }

    
}
