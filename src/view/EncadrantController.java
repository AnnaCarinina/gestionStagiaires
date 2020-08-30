/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
import helper.EncadrantFxHelper;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author LEILA
 */
public class EncadrantController implements Initializable {

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
    private Button encadrant;
    
    @FXML
    private Button departement;
        
    @FXML
    private Button seDeconnecter;
    
      @FXML 
    private Pane paneAdd;
    
    @FXML 
    private Pane paneRecherche;
    
    @FXML 
    private Pane paneEdit;
    
     @FXML
    private Button rechercher;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private Button editer;
    
    
    @FXML
    private TextField user;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField email;
    
    @FXML
    private RadioButton rb1 = new RadioButton();

    @FXML
    private RadioButton rb2 = new RadioButton();
    
    @FXML
    private ComboBox<Departement> dep;
    
    @FXML
    private ComboBox<Stagee> stagee;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private PasswordField passEdit;
    
    @FXML
    private TextField nom;
    
    @FXML
    private TextField prenom;
    
    @FXML
    private TextField tel;
    
    @FXML
    private TextField telEdit;
    
    @FXML
    private TextField mail;
    
    @FXML
    private TextArea adresse;
    
    @FXML
    private TextArea adresseEdit;
    
    @FXML
    private Label message;
    
    @FXML
    private ComboBox<Departement> departementComboBox;
    
    
    
    @FXML
    private RadioButton female = new RadioButton();

    @FXML
    private RadioButton male = new RadioButton();
    
    @FXML
    private TableView encadrantTableView = new TableView();
    
    @FXML
    private TextField usernameEdit;
    
    @FXML
    private TextField nameEdit;
    
    @FXML
    private TextField prenomEdit;
    
    @FXML
    private TextField emailEdit;
    
    @FXML
    private RadioButton rb1Edit = new RadioButton();

    @FXML
    private RadioButton rb2Edit = new RadioButton();
    
    @FXML
    private ComboBox<Departement> departementEdit;
    
            
    
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    StageeFacade stageeFacade = new StageeFacade();
    EncadrantFxHelper encadrantFxHelper;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initComboBox();
         initHelper();
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
    public void ajoute(ActionEvent actionEventAdd) {
        Encadrant encadrant = getParam(actionEventAdd);
        if (encadrant != null) {
            encadrantFacade.create(encadrant);
            actualiser();
        }
    }
     private Encadrant getParam(ActionEvent ae) {
        
    String gender = "";
        if (male.isSelected()) {
            gender = "M";
        } else if (female.isSelected()) {
            gender = "F";
        }

        if (!user.getText().equals("") && !pass.getText().equals("") && !mail.getText().equals("") && !nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("") && !tel.getText().equals("") && !gender.equals("") && !departementComboBox.getValue().equals("")) {
            Encadrant encadrant = new Encadrant(user.getText(), pass.getText(), nom.getText(), prenom.getText(), adresse.getText(), tel.getText(), mail.getText(), gender, departementComboBox.getValue());
            encadrant.setBlocked(0);
            encadrant.setRoot(0);
            return encadrant;
        } else {
            alertChampsVide(ae);
            return null;
        }
    }
     
       private void actualiser() {
        user.setText("");
        pass.setText("");
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        tel.setText("");
        mail.setText("");
        male.setSelected(false);
        female.setSelected(false);
        departementComboBox.setValue(null);
        
        usernameEdit.setText("");
        passEdit.setText("");
        nameEdit.setText("");
        prenomEdit.setText("");
        adresseEdit.setText("");
        telEdit.setText("");
        emailEdit.setText("");
        rb1Edit.setSelected(false);
        rb2Edit.setSelected(false);
        departementEdit.setValue(null);
        
    }
       
        private void alertChampsVide(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Quelque Champs sont Vide!");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
        
        
        private void initComboBox() {
        departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        dep.setItems(FXCollections.observableArrayList(departementFacade.findAll())); 
        stagee.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
    }
        
         @FXML
    public void recherche(ActionEvent actionEventRech) {
        String gender = "";
        if (rb1.isSelected()) {
            gender = "M";
        } else if (rb2.isSelected()) {
            gender = "F";
        }
        message.setVisible(true);
        encadrantTableView.setVisible(true);
        encadrantFxHelper.setList(encadrantFacade.recherchEncadrants(username.getText(), name.getText(), firstName.getText(), gender, stagee.getValue(), dep.getValue(), email.getText()));
    }
    
    
     private void initHelper() {
        encadrantFxHelper = new EncadrantFxHelper(encadrantTableView);
    }
     
        @FXML
    public void delete(ActionEvent actionEventAdd) {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        System.out.println(encadrant.getLogin());
        if (encadrant != null) {
            encadrantFacade.remove(encadrant);
            //encadrantFxHelper.setList(encadrantFacade.findAll());
            encadrantFxHelper.getTable().getItems().remove(encadrantFxHelper.getSelected());
        }
    }
    
     @FXML
    public void getForEdit() {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        fillField(encadrant);
    }

     public void fillField(Encadrant encadrant) {
        if (encadrant != null) {
            paneEdit.toFront();
            nameEdit.setText(encadrant.getNom());
            prenomEdit.setText(encadrant.getPrenom());
            telEdit.setText(encadrant.getTelephone());
            usernameEdit.setText(encadrant.getLogin());
            passEdit.setText(encadrant.getPassword());
            emailEdit.setText(encadrant.getEmail());
            adresseEdit.setText(encadrant.getAdresse());
            departementEdit.setValue(encadrant.getDepartement());
            if (encadrant.getGender().equals("M")) {
                rb1Edit.setSelected(true);
            }
            if (encadrant.getGender().equals("F")) {
                rb2Edit.setSelected(true);
            }
            
        }
    }
     
     @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        Encadrant encadrant = encadrantFxHelper.getSelected();
        if (encadrant != null || Session.getAttribut("encadrantEdit") != null) {
            String gender = "";
            if (rb1Edit.isSelected()) {
                gender = "M";
            } else if (rb2Edit.isSelected()) {
                gender = "F";
            }
            if (!nameEdit.getText().equals("") || !prenomEdit.getText().equals("") || !adresseEdit.getText().toString().equals("") || !gender.equals("") || !usernameEdit.getText().equals("") || !passEdit.getText().equals("") || !telEdit.getText().equals("") || !adresseEdit.getText().equals("") || !emailEdit.getText().equals("") || !departementEdit.getValue().equals(null)) {
                encadrant.setNom(nameEdit.getText());
                encadrant.setPrenom(prenomEdit.getText());
                encadrant.setAdresse(adresseEdit.getText());
                encadrant.setPassword(passEdit.getText());
                encadrant.setGender(gender);
                encadrant.setLogin(usernameEdit.getText());
                encadrant.setEmail(emailEdit.getText());
                encadrant.setTelephone(telEdit.getText());
                encadrant.setDepartement(departementEdit.getValue());
                encadrantFacade.edit(encadrant);
                int i = encadrantFxHelper.getTable().getItems().indexOf(encadrantFxHelper.getSelected());
                encadrantFxHelper.getTable().getItems().set(i, encadrant);
                actualiser();
                if (encadrant != null) {
                    paneEdit.toFront();
                } else if (Session.getAttribut("encadrantEdit") != null) {
                    Session.setAttribut(-1D, "encadrantEdit");
                        Acceuil.forward(actionEvent, "encadrant.fxml", this.getClass());
                }
            } else {
                alert(actionEvent);
            }
        } else {
            alertedit(actionEvent);
        }
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