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
import helper.StagiaireFxHelper;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class MenuEncaController implements Initializable {

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
    private Button retour3;
    
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
    private Pane paneStagiaire;
     
    @FXML
    private Pane paneTache;
    
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

        @FXML
    private TextField tacheAvanc;
    
    @FXML
    private Button editAvancButton;
    
    @FXML
    private AnchorPane tacheEditAvanc;
    
    @FXML
    private TableView tacheTableView = new TableView();
    
   
    
    @FXML
    private Label tacheInfo;
    
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
       // initComboBox();
        initHelper();
         initStageData();
         
          if (Session.getAttribut("stagiaireEdit") != null) {
            Stagiaire stagiaire = (Stagiaire) Session.getAttribut("stagiaireEdit");
            stagiairePane.toFront();
            fillField(stagiaire);
        }
 
        if (Session.getAttribut("tacheEdit") instanceof Double) {
             try {
                 Acceuil.showView("Tache.fxml", this.getClass());
             } catch (IOException ex) {
                 Logger.getLogger(MenuEncaController.class.getName()).log(Level.SEVERE, null, ex);
             }
            Session.setAttribut(null, "tacheEdit");
        }
        if (Session.getAttribut("stagiaireEdit") instanceof Double) {
           try {
                 Acceuil.showView("Stagiaire.fxml", this.getClass());
             } catch (IOException ex) {
                 Logger.getLogger(MenuEncaController.class.getName()).log(Level.SEVERE, null, ex);
             }
            Session.setAttribut(null, "stagiaireEdit");
        }
    }    
    
    @FXML
    private void handleActionButton(ActionEvent event) throws IOException{
        if(event.getSource().equals(tache)){
             paneTache.toFront();
        }
        if(event.getSource().equals(retour)){
            Acceuil.forward(event, "EditeProfile.fxml", this.getClass());
        }
        
        if(event.getSource().equals(retour1)){
             paneStagiaire.toFront();
        }
                      
        
        if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
        
        if(event.getSource().equals(retour)){
            Acceuil.forward(event, "EditeProfile.fxml", this.getClass());
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

        //Stagiaires
        
     
    
     @FXML
    private AnchorPane stagiairePane;

   
    @FXML
    private Button rechercher;
    
    @FXML
    private Button ajouter;
    
    @FXML
    private Button mesStagiaires;
    
    @FXML
    private Button editer;
    
               
    @FXML 
    private Pane paneAdd;
    
    @FXML 
    private Pane paneRecherche;
    
    @FXML 
    private Pane paneMesStagiaires;
    
    @FXML 
    private Pane paneStage;
    
    @FXML
    private TextField nomm;

    @FXML
    private TextField prenom;

    @FXML
    private RadioButton rb1 = new RadioButton();

    @FXML
    private RadioButton rb2 = new RadioButton();

    @FXML
    private DatePicker dateNaissance = new DatePicker();

    @FXML
    private TextField email;

    @FXML
    private TextField niveau;

    @FXML
    private TextField filiere;

    @FXML
    private TextField telephone;

    @FXML
    private TextArea adresse;

    @FXML
    private TextField etablissement;
    @FXML
    private ComboBox<Stagee> stageeComboBox = new ComboBox<>();
    
    
    @FXML
    private TextField nomEd;

    @FXML
    private TextField prenomEd;

    @FXML
    private RadioButton rb1Ed = new RadioButton();

    @FXML
    private RadioButton rb2Ed = new RadioButton();

    @FXML
    private DatePicker dateNaissanceEd = new DatePicker();

    @FXML
    private TextField emailEd;

    @FXML
    private TextField niveauEd;

    @FXML
    private TextField filiereEd;

    @FXML
    private TextField telephoneEd;

    @FXML
    private TextArea adresseEd;
    
    @FXML
    private ImageView back;
    
    @FXML
    private ImageView goBack;
    
    @FXML
    private ImageView back1;

    @FXML
    private TextField etablissementEd;
    @FXML
    private ComboBox<Stagee> stageeComboBoxEd = new ComboBox<>();
    @FXML
    private ComboBox<Departement> departementComboBoxEd = new ComboBox<>();
    @FXML
    private ComboBox<Encadrant> encadrantComboBoxEd = new ComboBox<>();
     @FXML
    private TextField nomRech;

    @FXML
    private TextField prenomRech;

    @FXML
    private Label message;
    @FXML
    private RadioButton rb1Rech = new RadioButton();

    @FXML
    private RadioButton rb2Rech = new RadioButton();

    @FXML
    private TableView stagiareTableView = new TableView();
    @FXML
    private TableView stagiareTableView1 = new TableView();
   
    @FXML
    private ComboBox<Stagee> stageeComboBoxRech = new ComboBox<>();
    @FXML
    private ComboBox<Departement> departementComboBoxRech = new ComboBox<>();
    @FXML
    private ComboBox<Encadrant> encadrantComboBoxRech = new ComboBox<>();



       StagiaireFxHelper stagiaireFxHelper;
       StagiaireFxHelper stagiaireFxHelper1;
    
 
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
               
        if(event.getSource().equals(stage)){
           paneStage.toFront();
        }
        
        if(event.getSource().equals(stagiaire)){
           paneStagiaire.toFront();
        }
        
         if(event.getSource().equals(rechercher)){
            paneRecherche.toFront();
        }
         
          if(event.getSource().equals(mesStagiaires)){
            paneMesStagiaires.toFront();
        }
          if(event.getSource().equals(ajouter)){
            paneAdd.toFront();
        }
          
           if(event.getSource().equals(editer)){
            paneEdit.toFront();
        }
           
        if(event.getSource().equals(retour)){
          Acceuil.forward(event, "EditeProfile.fxml", this.getClass());
        }
           if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
    }
     @FXML
    public void ajoute(ActionEvent actionEventAdd) {
       Stagiaire stagiaire = getParam(actionEventAdd);
        if (stagiaire != null) {
            stagiaireFacade.create(stagiaire);
            actualiser();
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
        }
    }
    
    private Stagiaire getParam(ActionEvent actionEvent) {
        String gender = "";
        if (rb1.isSelected()) {
            gender = "M";
        } else if (rb2.isSelected()) {
            gender = "F";
        }
        if (!nomm.getText().equals("") && !prenom.getText().equals("") && !dateNaissance.getEditor().getText().equals("") && !gender.equals("") && !etablissement.getText().equals("") && !niveau.getText().equals("") && !filiere.getText().equals("") && !telephone.getText().equals("") && !adresse.getText().equals("") && !email.getText().equals("")) {
            System.out.print(encadrant.getDepartement().getNom());
            Stagiaire stagiaire = new Stagiaire(nomm.getText(), prenom.getText(), DateUtil.convert(dateNaissance.getEditor().getText()), gender, etablissement.getText(), niveau.getText(), filiere.getText(), telephone.getText(), adresse.getText(), email.getText(), encadrant.getStagee(), encadrant.getDepartement(), encadrant);
            return stagiaire;
        }
        alert(actionEvent);
        return null;
    }

     
     public void actualiser() {
        nomm.setText("");
        prenom.setText("");
        rb1.setSelected(false);
        rb2.setSelected(false);
        dateNaissance.getEditor().setText("");
        email.setText("");
        niveau.setText("");
        filiere.setText("");
        telephone.setText("");
        adresse.setText("");
        etablissement.setText("");
        stageeComboBox.setValue(null);
//        departementComboBox.setValue(null);
//        encadrantComboBox.setValue(null);
        nomRech.setText("");
        prenomRech.setText("");
        rb1Rech.setSelected(false);
        rb2Rech.setSelected(false);
        stageeComboBoxRech.setValue(null);
        //   departementComboBoxRech.setValue(null);
      //  encadrantComboBoxRech.setValue(null);
        nomEd.setText("");
        prenomEd.setText("");
        rb1Ed.setSelected(false);
        rb2Ed.setSelected(false);
        dateNaissanceEd.getEditor().setText("");
        emailEd.setText("");
        niveauEd.setText("");
        filiereEd.setText("");
        telephoneEd.setText("");
        adresseEd.setText("");
        etablissementEd.setText("");
    //    stageeComboBoxEd.setValue(null);
     //   departementComboBoxEd.setValue(null);
      //  encadrantComboBoxEd.setValue(null);
    }
     
       private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
       
    
    
    @FXML
    public void edit(ActionEvent actionEvent) throws IOException {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        if (stagiaire != null || Session.getAttribut("stagiaireEdit") != null) {
            String gender = "";
            if (rb1Ed.isSelected()) {
                gender = "M";
            } else if (rb2Ed.isSelected()) {
                gender = "F";
            }
            if (!nomEd.getText().equals("") || !prenomEd.getText().equals("") || !dateNaissanceEd.getEditor().getText().equals("") || !gender.equals("") || !etablissementEd.getText().equals("") || !niveauEd.getText().equals("") || !filiereEd.getText().equals("") || !telephoneEd.getText().equals("") || !adresseEd.getText().equals("") || !emailEd.getText().equals("") || !stageeComboBoxEd.getValue().equals(null) || !departementComboBoxEd.getValue().equals(null) || !encadrantComboBoxEd.getValue().equals(null)) {
                stagiaire.setNom(nomEd.getText());
                stagiaire.setPrenom(prenomEd.getText());
                stagiaire.setDateNaissance(DateUtil.convert(dateNaissanceEd.getEditor().getText()));
                stagiaire.setGender(gender);
                stagiaire.setEtablissement(etablissementEd.getText());
                stagiaire.setNiveau(niveauEd.getText());
                stagiaire.setFiliere(filiereEd.getText());
                stagiaire.setTelephone(telephoneEd.getText());
                stagiaire.setAdresse(adresseEd.getText());
                stagiaire.setEmail(emailEd.getText());
                stagiaire.setStagee(encadrant.getStagee());
                stagiaire.setDepartement(encadrant.getDepartement());
                stagiaire.setEncadrant(encadrant);
                stagiaireFacade.edit(stagiaire);
                int i = stagiaireFxHelper.getTable().getItems().indexOf(stagiaireFxHelper.getSelected());
                stagiaireFxHelper.getTable().getItems().set(i, stagiaire);
                actualiser();
                if (stagiaire != null) {
                    paneEdit.toFront();
                } else if (Session.getAttribut("stagiaireEdit") != null) {
                    Session.setAttribut(-1D, "stagiaireEdit");
                        Acceuil.forward(actionEvent, "StageView.fxml", this.getClass());
                }
            } else {
                alert(actionEvent);
            }
        } else {
            alertedit(actionEvent);
        }
    }
    
        private void alertedit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERREUR");
        alert.setContentText("Selectionner un element a modifier dans le volet de Recherche!! ");
        alert.setHeaderText("ATTENTION! Aucun element a modifier!!");
        alert.showAndWait();
    }
    private void initHelper() {
        stagiaireFxHelper = new StagiaireFxHelper(stagiareTableView);
        stagiaireFxHelper1 = new StagiaireFxHelper(stagiareTableView1, stagiaireFacade.findByEnca(encadrant));
        tacheFxHelper = new TacheFxHelper(tacheTableView, tacheFacade.findByStage(this.encadrant.getStagee()));
    }

    private void initComboBox() {
        stageeComboBox.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
       // departementComboBox.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
//        encadrantComboBox.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        stageeComboBoxEd.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
       // departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxEd.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
        stageeComboBoxRech.setItems(FXCollections.observableArrayList(stageeFacade.findAll()));
        departementComboBoxRech.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
        encadrantComboBoxRech.setItems(FXCollections.observableArrayList(encadrantFacade.findAll()));
    }
    
        public void fillField(Stagiaire stagiaire) {
        if (stagiaire != null) {
            paneEdit.toFront();
            nomEd.setText(stagiaire.getNom());
            prenomEd.setText(stagiaire.getPrenom());
            adresseEd.setText(stagiaire.getAdresse());
            if (stagiaire.getGender().equals("M")) {
                rb1Ed.setSelected(true);
            }
            if (stagiaire.getGender().equals("F")) {
                rb2Ed.setSelected(true);
            }
            emailEd.setText(stagiaire.getEmail());
            dateNaissanceEd.getEditor().setText(DateUtil.convertToString(stagiaire.getDateNaissance()));
            niveauEd.setText(stagiaire.getNiveau() + "");
            filiereEd.setText(stagiaire.getFiliere());
            telephoneEd.setText(stagiaire.getTelephone());
            etablissementEd.setText(stagiaire.getEtablissement());
           // stageeComboBoxEd.getSelectionModel().select(stagiaire.getStagee());
           // departementComboBoxEd.getSelectionModel().select(stagiaire.getDepartement());
          //  encadrantComboBoxEd.getSelectionModel().select(stagiaire.getEncadrant());
        }
    }
        
   @FXML
    public void getForEdit() {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        fillField(stagiaire);
    }
    
      @FXML
    public void delete(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = stagiaireFxHelper.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
           stagiaireFxHelper.setList(stagiaireFacade.findByEnca(encadrant));
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
            //stagiaireFxHelper.getTable().getItems().remove(stagiaireFxHelper.getSelected());
        }
    }
    
      @FXML
    public void recherche(ActionEvent actionEventRech) {
        String gender = "";
        if (rb1Rech.isSelected()) {
            gender = "M";
        } else if (rb2Rech.isSelected()) {
            gender = "F";
        }
        message.setVisible(true);
        stagiareTableView.setVisible(true);
        stagiaireFxHelper.setList(stagiaireFacade.recherchStagiaire(nomRech.getText(), prenomRech.getText(), gender, stageeComboBoxRech.getValue(), departementComboBoxRech.getValue(), encadrantComboBoxRech.getValue()));
    }
    
    @FXML
private void handleMouseClicked(MouseEvent event){
    if(event.getSource().equals(back)) {
            paneAdd.toFront();
    } 
    if(event.getSource().equals(back1)) {
            paneRecherche.toFront();
    } 
    
    if(event.getSource().equals(goBack)) {
            paneMesStagiaires.toFront();
    } 
}

 public void getForEdit1(ActionEvent actionEvent) {
        Stagiaire stagiaire = stagiaireFxHelper1.getSelected();
        fillField(stagiaire);
    }
 
 
    @FXML
    public void delete1(ActionEvent actionEventAdd) {
        Stagiaire stagiaire = stagiaireFxHelper1.getSelected();
        if (stagiaire != null) {
            stagiaireFacade.remove(stagiaire);
            stagiaireFxHelper1.setList(stagiaireFacade.findByEnca(encadrant));
            //stagiaireFxHelper.getTable().getItems().remove(stagiaireFxHelper.getSelected());
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
    
}
