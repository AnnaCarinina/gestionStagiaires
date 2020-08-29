/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
import bean.Stagiaire;
import bean.Tache;
import helper.StageeFxHelper;
import helper.StagiaireFxHForDep;
import helper.TacheFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;
import service.TacheFacade;
import util.DateUtil;
import util.Session;

/**
 * FXML Controller class
 *
 * @author LEILA
 */
public class StageViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button stage;
    
    @FXML
    private Button stagiaire;
    
    @FXML
    private Button tache;
    
    @FXML
    private Button seDeconnecter;
        @FXML
    private Button retour;
                @FXML
    private Button retour1;
    
    @FXML 
    private Pane paneEdit;
    
    @FXML
    private TextField nom;

    @FXML
    private TextField budget;

    @FXML
    private DatePicker dateDeb;

    @FXML
    private DatePicker dateFin;

    @FXML
    private ComboBox<Encadrant> encadrantComboBox;

    @FXML
    private ComboBox<Departement> departementComboBox;
    
        @FXML
    private Label stageLabel;
    @FXML
    private Label encaLabel;
    @FXML
    private Label deptLabel;
    @FXML
    private Label avancLabel;
    @FXML
    private Label budgetLabel;

    StageeFacade stageeFacade = new StageeFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    TacheFacade tacheFacade = new TacheFacade();
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    StageeFxHelper stageeFxHelper;
    TacheFxHelper tacheFxHelper;
    StagiaireFxHForDep stagiaireFxHForDep;
    
      Encadrant encadrant = (Encadrant) Session.getAttribut("encaConnect");
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         initStageData();
 
        if (Session.getAttribut("tacheEdit") instanceof Double) {
             try {
                 Acceuil.showView("Tache.fxml", this.getClass());
             } catch (IOException ex) {
                 Logger.getLogger(StageViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
            Session.setAttribut(null, "tacheEdit");
        }
        if (Session.getAttribut("stagiaireEdit") instanceof Double) {
           try {
                 Acceuil.showView("Stagiaire.fxml", this.getClass());
             } catch (IOException ex) {
                 Logger.getLogger(StageViewController.class.getName()).log(Level.SEVERE, null, ex);
             }
            Session.setAttribut(null, "stagiaireEdit");
        }
    }    
    
    @FXML
    private void handleActionButton(ActionEvent event) throws IOException{
        if(event.getSource().equals(tache)){
            Acceuil.forward(event, "Tache.fxml", this.getClass());
        }
                if(event.getSource().equals(retour)){
            Acceuil.forward(event, "EditeProfile.fxml", this.getClass());
        }
                        if(event.getSource().equals(retour1)){
            Acceuil.forward(event, "Stagiaire.fxml", this.getClass());
        }
                      if(event.getSource().equals(stagiaire)){
            Acceuil.forward(event, "Stagiaire.fxml", this.getClass());
        }
        
        if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
    }
    
        public void initStageData() {
        Stagee stagee = encadrant.getStagee();
        stageLabel.setText(stagee.toString());
        encaLabel.setText(encadrant.toString());
        deptLabel.setText(stagee.getDepartement().toString());
        avancLabel.setText(stagee.getAvancement().toString());
        budgetLabel.setText(stagee.getBudget().toString());
    }


    
  
  
}
