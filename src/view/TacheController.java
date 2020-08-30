/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Stagee;
import bean.Tache;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import service.DepartementFacade;
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
    private Pane paneTache;
    
      @FXML
    private Button encadrant;
     
      @FXML
    private Button departement;
      
    @FXML
    private Button stage;
    
    @FXML
    private Button stagiaire;
    
    @FXML
    private Button rechercher;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private Button editer;
    
    @FXML
    private Button seDeconnecter;
    
    @FXML 
    private Pane paneAdd;
    
    @FXML 
    private Pane paneRecherche;
    
    @FXML 
    private Pane paneEdit;
    
    @FXML
    private TextField nom;

    @FXML
    private TextField avancement;

    @FXML
    private TextField importance;

    @FXML
    private ComboBox<Stagee> stageComboBox;
    
    /*
    ******
    ******** Recherche View Attributes
    *****
     */
    @FXML
    private TextField nomRec;

    @FXML
    private TextField avancementRecMax;
    @FXML
    private TextField avancementRecMin;

    @FXML
    private TextField importanceRecMax;
    @FXML
    private TextField importanceRecMin;

    @FXML
    private ComboBox<Stagee> stageComboBoxRech;

    @FXML
    private TableView tacheTableView;
    
    /*
    ******
    ******** Edit View Attributes
    *****
     */

    @FXML
    private TextField nomEd;

    @FXML
    private TextField avancementEd;

    @FXML
    private TextField importanceEd;

    @FXML
    private ComboBox<Stagee> stageComboBoxEd;

    TacheFxHelper tacheFxHelper;
    TacheFacade tacheFacade = new TacheFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageeFacade = new StageeFacade();

     @FXML
    void ajout(ActionEvent event) {
        Tache tache = getParam(event);
        if (tache != null) {
            tacheFacade.create(tache);
            stageeFacade.calculeAvanc(tache.getStagee());
            actualiser();
        }
    }
    
    @FXML
    public void getForEdit() {
        Tache tache = tacheFxHelper.getSelected();
        fillFields(tache);

    }

    public void fillFields(Tache tache) {
        if (tache != null) {
            paneEdit.toFront();
            nomEd.setText(tache.getNom());
            avancementEd.setText(tache.getAvancement() + "");
            importanceEd.setText(tache.getImportance() + "");
            stageComboBoxEd.setValue(tache.getStagee());
        }
    }

    private Tache getParam(ActionEvent actionEvent) {
        if (!avancement.getText().equals("") && !importance.getText().equals("") && !nom.getText().equals("") && !stageComboBox.getValue().equals(null)) {
            Tache sujet = new Tache(nom.getText(), new Double(avancement.getText()), new Double(importance.getText()), stageComboBox.getValue());
            return sujet;
        } else {
            alert(actionEvent);
            return null;
        }

    }
    
    @FXML
    void edit(ActionEvent event) throws IOException {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null || Session.getAttribut("tacheEdit") != null) {
            if (!avancementEd.getText().equals("") && !importanceEd.getText().equals("") && !nomEd.getText().equals("") && !stageComboBoxEd.getValue().equals(null)) {
                tache.setAvancement(new Double(avancementEd.getText()));
                tache.setImportance(new Double(importanceEd.getText()));
                tache.setNom(nomEd.getText());
                tache.setStagee(stageComboBoxEd.getValue());
                tacheFacade.edit(tache);
                stageeFacade.calculeAvanc(tache.getStagee());
                int i = tacheFxHelper.getTable().getItems().indexOf(tacheFxHelper.getSelected());
                tacheFxHelper.getTable().getItems().set(i, tache);
                actualiser();
                if (tache != null) {
                    paneRecherche.toFront();
                } else if (Session.getAttribut("tacheEdit") != null) {
                    Session.setAttribut(-1D, "tacheEdit");
                    Acceuil.forward(event, "StageView.fxml", this.getClass());
                }
            } else {
                alert(event);
            }
        } else {
            alertedit(event);
        }
    }

     @FXML
    public void delete(ActionEvent actionEvent) {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            Stagee s = tache.getStagee();
            
            
            int i = tacheFxHelper.getTable().getItems().indexOf(tacheFxHelper.getSelected());
            tacheFxHelper.getTable().getItems().remove(i);
            tacheFacade.remove(tache);
            tacheFxHelper.getTable().getSelectionModel().clearSelection();
            //tacheFxHelper.setList(tacheFacade.findAll());
            stageeFacade.calculeAvanc(s);
        }
    }
    
     @FXML
    public void recherch(ActionEvent event) {
        Double amx;
        Double amn;
        if (avancementRecMax.getText().equals("")) {
            amx = null;
        } else {
            amx = new Double(avancementRecMax.getText());
        }
        if (avancementRecMin.getText().equals("")) {
            amn = null;
        } else {
            amn = new Double(avancementRecMin.getText());
        }
        Double imx;
        Double imn;
        if (importanceRecMax.getText().equals("")) {
            imx = null;
        } else {
            imx = new Double(importanceRecMax.getText());
        }
        if (importanceRecMin.getText().equals("")) {
            imn = null;
        } else {
            imn = new Double(importanceRecMin.getText());
        }
        tacheFxHelper.setList(tacheFacade.recherchTache(nomRec.getText(), amx, amn, imx, imn, stageComboBoxRech.getValue()));
        actualiser();
    }
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();

        if (Session.getAttribut("tacheEdit") != null) {
            Tache tache = (Tache) Session.getAttribut("tacheEdit");
            paneEdit.toFront();
            fillFields(tache);
        }
    }    
    
    @FXML
    private void handleActionButton(ActionEvent event) throws IOException{
        
     
         if(event.getSource().equals(stage)){
            Acceuil.forward(event, "StageView.fxml", this.getClass());
        }
        
        
        if(event.getSource().equals(stagiaire)){
             Acceuil.forward(event, "Stagiaire.fxml", this.getClass());
        }
        
         if(event.getSource().equals(departement)){
            Acceuil.forward(event, "Departement.fxml", this.getClass());
        }
         
          if(event.getSource().equals(encadrant)){
            Acceuil.forward(event, "encadrant.fxml", this.getClass());
        }
          
        if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
   
        
         if(event.getSource().equals(rechercher)){
            paneRecherche.toFront();
        }
         
          if(event.getSource().equals(ajouter)){
            paneAdd.toFront();
        }
          
           if(event.getSource().equals(editer)){
            paneEdit.toFront();
        }
          
          
    }
    
     private void initComboBox() {
        stageComboBox.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        stageComboBoxEd.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        stageComboBoxRech.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
    }

    private void initHelper() {
        tacheFxHelper = new TacheFxHelper(tacheTableView);
    }

    private void actualiser() {
        nomRec.setText("");
        avancementRecMax.setText("");
        avancementRecMin.setText("");
        importanceRecMax.setText("");
        importanceRecMin.setText("");
        stageComboBoxRech.setValue(null);
        nom.setText("");
        avancement.setText("");
        importance.setText("");
        stageComboBox.setValue(null);
        nomEd.setText("");
        avancementEd.setText("");
        importanceEd.setText("");
        stageComboBoxEd.setValue(null);

    }

    private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertedit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERREUR");
        alert.setContentText("Selectionner un element a modifier dans le volet de Recherche!! ");
        alert.setHeaderText("ATTENTION! Aucun element a modifier!!");
        alert.showAndWait();
    }

    
}
