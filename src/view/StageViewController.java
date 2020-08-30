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
import java.util.List;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Button encadrant;
     
      @FXML
    private Button departement;
      
    @FXML
    private Button stagiaire;
    
    @FXML
    private Button tache;
    
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
    private Pane paneStage;
    
    @FXML 
    private Pane paneRecherche;
    
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

    StageeFacade stageeFacade = new StageeFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    TacheFacade tacheFacade = new TacheFacade();
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    StageeFxHelper stageeFxHelper;
    TacheFxHelper tacheFxHelper;
    StagiaireFxHForDep stagiaireFxHForDep;
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initComboBox();
        initHelper();
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
        
        if(event.getSource().equals(stage)){
            Acceuil.forward(event, "StageView.fxml", this.getClass());
        }
        
        if(event.getSource().equals(tache)){
            Acceuil.forward(event, "Tache.fxml", this.getClass());
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
    
       @FXML
    private void ajout(ActionEvent event) {
        Stagee stagee = getParam(event);
        if (stagee != null) {
         //   stageeFacade.calculeAvanc(stagee);
         stagee.setAvancement(0.0);
         stageeFacade.create(stagee);
         stagee.getEncadrant().setStagee(stagee);
         encadrantFacade.edit(stagee.getEncadrant());
         actualiser();
        }
    }
    
        private Stagee getParam(ActionEvent actionEvent) {
        if (!nom.getText().equals("") && !budget.getText().equals("") && !dateDeb.getEditor().getText().equals("") && !dateFin.getEditor().getText().equals("") && !encadrantComboBox.getValue().equals("") && !departementComboBox.getValue().equals("")) {
            Stagee stagee = new Stagee(nom.getText(), DateUtil.convert(dateDeb.getEditor().getText()), DateUtil.convert(dateFin.getEditor().getText()), new Double(budget.getText()), departementComboBox.getValue(), encadrantComboBox.getValue());
            return stagee;
        } else {
            alert(actionEvent);
            return null;
        }
    }
        
         private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
         
            public void actualiser() {
     
        nom.setText("");
        dateDeb.getEditor().setText("");
        dateFin.getEditor().setText("");
        encadrantComboBox.setValue(null);
        departementComboBox.setValue(null);
        budget.setText("");
   
    }
            
      private void initComboBox() {
        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBox.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxEd.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        departementComboBoxRech.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxRech.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
    }
      
       @FXML
    private TextField nomEd;

    @FXML
    private DatePicker dateDebEd;

    @FXML
    private DatePicker dateFinEd;

    @FXML
    private ComboBox<Encadrant> encadrantComboBoxEd;

    @FXML
    private ComboBox<Departement> departementComboBoxEd;

    @FXML
    private TextField budgetEd;
        @FXML
    public void edit(ActionEvent event) {
        Stagee s = stageeFxHelper.getSelected();
        if (s != null) {
            Stagee stagee = stageeFacade.find(s.getId());
            if (stagee != null && !nomEd.getText().equals("") && !budgetEd.getText().equals("") && !dateDebEd.getEditor().getText().equals("") && !dateFinEd.getEditor().getText().equals("") && !encadrantComboBoxEd.getValue().equals("") && !departementComboBoxEd.getValue().equals("")) {
                stagee.setNom(nomEd.getText());
                stagee.setBudget(new Double(budgetEd.getText()));
                stagee.setDateDeb(DateUtil.convert(dateDebEd.getValue().toString()));
                stagee.setDateFin(DateUtil.convert(dateFinEd.getValue().toString()));
                stagee.setDepartement(departementComboBoxEd.getValue());
                stagee.setEncadrant(encadrantComboBoxEd.getValue());
                stageeFacade.calculeAvanc(stagee);
                stageeFacade.edit(stagee);
                int i = stageeFxHelper.getTable().getItems().indexOf(stageeFxHelper.getSelected());
                stageeFxHelper.getTable().getItems().set(i, stagee);
                actualiser();
                paneStage.toFront();
            } else {
                alert(event);
            }
        } else {
            alertedit(event);
        }
    }
    
    private void alertedit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERREUR");
        alert.setContentText("Selectionner un element a modifier dans le volet de Recherche!! ");
        alert.setHeaderText("ATTENTION! Aucun element a modifier!!");
        alert.showAndWait();
    }
    
        @FXML
    public void delete(ActionEvent actionEvent) {
        Stagee stagee = stageeFxHelper.getSelected();
        if (stagee != null) {
            stageeFacade.delete(stagee);
            stageeFxHelper.setList(stageeFacade.findAll());
            tacheFxHelper.setList(null);
            stagiaireFxHForDep.setList(null);
        }
    }
    
     @FXML
    private TextField nomRech;
    @FXML
    private TextField budgetMax;
    @FXML
    private TextField budgetMin;

    @FXML
    private ComboBox<Encadrant> encadrantComboBoxRech;

    @FXML
    private ComboBox<Departement> departementComboBoxRech;

    @FXML
    private TableView stageTableView;
    @FXML
    private TableView tacheTableView;
    @FXML
    private TableView stagiaireTableView;

    @FXML
    public void recherch(ActionEvent event) {
        Double bmx;
        Double bmn;
        if (budgetMax.getText().equals("")) {
            bmx = null;
        } else {
            bmx = new Double(budgetMax.getText());
        }
        if (budgetMin.getText().equals("")) {
            bmn = null;
        } else {
            bmn = new Double(budgetMin.getText());
        }

        stageeFxHelper.setList(stageeFacade.recherchStage(nomRech.getText(), bmx, bmn, departementComboBoxRech.getValue(), encadrantComboBoxRech.getValue()));
    }
@FXML
    public void supprStagiaire(ActionEvent ae) throws IOException {
        Stagiaire stagiaire = stagiaireFxHForDep.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
            stagiaireFxHForDep.setList(stagiaireFacade.findAll());
        }
    }
    
      @FXML
    public void editStagiaire(ActionEvent ae) throws IOException {
        Stagiaire stagiaire = stagiaireFxHForDep.getSelected();
        if (stagiaire != null) {
            Session.setAttribut(stagiaire, "stagiaireEdit");
            Acceuil.forward(ae, "Stagiaire.fxml", this.getClass());
        }
    }

       @FXML
    public void supprTache(ActionEvent ae) {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
            Stagee s = tache.getStagee();
            tacheFacade.remove(tache);
            tacheFxHelper.setList(tacheFacade.findByStage(s));
            stageeFacade.calculeAvanc(s);
            stageeFxHelper.getTable().refresh();
        }
    }
    
        @FXML
    public void editTache(ActionEvent ae) throws IOException {
        Tache tache = tacheFxHelper.getSelected();
        if (tache != null) {
           // Session.setAttribut(tache, "tacheEdit");
            Acceuil.forward(ae, "Tache.fxml", this.getClass());
        }
    }
  private void initHelper() {
        stageeFxHelper = new StageeFxHelper(stageTableView);
        tacheFxHelper = new TacheFxHelper(tacheTableView);
        stagiaireFxHForDep = new StagiaireFxHForDep(stagiaireTableView);
    }
  
  
}
