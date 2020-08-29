/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Departement;
import bean.Encadrant;
import bean.Stagee;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author HPELITEBOOK
 */
public class EditeProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
         init();
         getForEdit();
      //  init();
        
    }   
    
    @FXML
    private Label encadrant;
    
    @FXML
    private Pane paneEdit;
    
    @FXML
    private Button gerer;
    
    @FXML
    private TextField password;
    
    @FXML
    private Button seDeconnecter;
    
    @FXML
    private Button editer;
    
    @FXML
    private Button annuler;
    
    @FXML
    private Pane acceuil;
    
      @FXML
    private TextField nomEd;

    @FXML
    private TextField prenomEd;

    @FXML
    private TextField telephoneEd;

    @FXML
    private TextField emailEd;

    @FXML
    private RadioButton rbMEd;

    @FXML
    private RadioButton rbFEd;

    @FXML
    private TextArea adresseEd;

    @FXML
    private ComboBox<Departement> departementComboBoxEd;

    @FXML
    private ComboBox<Stagee> stageeComboBoxEd;
    
    Encadrant e = (Encadrant) Session.getAttribut("encaConnect");
    
    
    StagiaireFacade stagiaireFacade = new StagiaireFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    StageeFacade stageFacade = new StageeFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();

    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        if(event.getSource().equals(gerer)){
            Acceuil.forward(event, "StageView.fxml", this.getClass());
        }
        if(event.getSource().equals(editer)){
            paneEdit.toFront();
        }
        if(event.getSource().equals(seDeconnecter)){
           Acceuil.forward(event, "welcome.fxml", this.getClass());
        }
        
        if(event.getSource().equals(annuler)){
          acceuil.toFront();
        }
        
     }
    
      @FXML 
      private void editer(ActionEvent event){
           String gender = "";
        if (rbMEd.isSelected()) {
            gender = "M";
        } else if (rbFEd.isSelected()) {
            gender = "F";
        }
        if (!password.getText().equals("") && !nomEd.getText().equals("") && !prenomEd.getText().equals("") && !adresseEd.getText().equals("") && !telephoneEd.getText().equals("") && !emailEd.getText().equals("") && !gender.equals("") && !departementComboBoxEd.getValue().equals("") && !stageeComboBoxEd.getValue().equals("")) {
            e.setPassword(password.getText());
            e.setNom(nomEd.getText());
            e.setPrenom(prenomEd.getText());
            e.setAdresse(adresseEd.getText());
            e.setTelephone(telephoneEd.getText());
            e.setEmail(emailEd.getText());
            e.setGender(gender);
            e.setDepartement(departementComboBoxEd.getValue());
            e.setStagee(stageeComboBoxEd.getValue());
            encadrantFacade.edit(e);
            acceuil.toFront();
        } else {
            alert(event);
        }

    }
      
         @FXML
    public void actuComboBox(ActionEvent actionEvent) {
        stageeComboBoxEd.setItems(FXCollections.observableArrayList(stageFacade.findByDep(departementComboBoxEd.getValue())));
    }

    private void initComboBox() {
        System.out.println("com");
        departementComboBoxEd.setItems(FXCollections.observableArrayList(departementFacade.findAll()));
    }
     private void alert(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur insertion");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
     
         @FXML
    private Pane user;

        public void init(){
         
        if (e.getRoot()==1){
           encadrant.setText(e.getNom()+" "+e.getPrenom()+" SuperUser");
            user.setVisible(false);
          
        }else{
            encadrant.setText(e.getNom()+" "+e.getPrenom());
            user.setVisible(true);
           
        }
        
        }
        

            
                public void getForEdit() {
        if (encadrant != null) {
            password.setText(e.getPassword());
            nomEd.setText(e.getNom());
            prenomEd.setText(e.getPrenom());
            adresseEd.setText(e.getAdresse());
            if (e.getGender().equals("M")) {
                rbMEd.setSelected(true);
            }
            if (e.getGender().equals("F")) {
                rbFEd.setSelected(true);
            }
            emailEd.setText(e.getEmail());
            telephoneEd.setText(e.getTelephone());
            stageeComboBoxEd.getSelectionModel().select(e.getStagee());
            departementComboBoxEd.getSelectionModel().select(e.getDepartement());
        }
    }
}
      
   
