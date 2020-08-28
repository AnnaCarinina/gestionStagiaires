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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class LoginRespoController implements Initializable {

    @FXML
    private Button seConnect;
     @FXML
    private ImageView back;
    
    @FXML
    private ImageView back1;
    
    @FXML
    private ImageView back2;
      
    @FXML
    private Button signUp;
    
        @FXML
    private Button inscription;
    
    @FXML
    private Button annuler;
    
    @FXML
    private Pane paneSignIn;
    
    @FXML
    private Pane paneSignUp;
    
    @FXML
    private Pane paneNext;
    
    @FXML
    private Button next;
    
    @FXML
    private Button Connect;
    
        @FXML
    private Button signUp1;
    
    @FXML
    private TextField utilisateur;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField user;
    
    @FXML
    private PasswordField pass;
    
    @FXML
    private TextField nom;
    
    @FXML
    private TextField prenom;
    
    @FXML
    private TextField tel;
    
    @FXML
    private TextField mail;
    
    @FXML
    private TextField adresse;
    
    @FXML
    private ComboBox<Departement> departement;
    
    @FXML
    private ComboBox<Stagee> stagee;
    
    @FXML
    private RadioButton female = new RadioButton();

    @FXML
    private RadioButton male = new RadioButton();
    
    @FXML
    private AnchorPane welcome = new AnchorPane();
    
     @FXML
    private AnchorPane id = new AnchorPane();
    
     StagiaireFacade stagiaireFacade = new StagiaireFacade();
    DepartementFacade departementFacade = new DepartementFacade();
    EncadrantFacade encadrantFacade = new EncadrantFacade();
    StageeFacade stageFacade = new StageeFacade();
    EncadrantFxHelper encadrantFxHelper;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       initComboBox();
    }    
      public void handleButtonAction(ActionEvent event){
         
        if(event.getSource().equals(Connect)){
             paneSignIn.toFront();
        }
        if(event.getSource().equals(signUp1)){
             paneSignIn.toFront();
        }
        if(event.getSource().equals(inscription)){
            paneSignUp.toFront();
        }

        if(event.getSource().equals(next)){
            if (!user.getText().equals("") && !pass.getText().equals("")) {
            int res = encadrantFacade.findByLogin(user.getText());
            if (res < 0) {
               paneNext.toFront();
            } else {
                alertLoginExist(event);
            }
        } else {
            alertChampsVide(event);
        }
        }
        if(event.getSource().equals(annuler)){
            welcome.toFront();
        }
    }
@FXML
private void handleMouseClicked(MouseEvent event){
    if(event.getSource().equals(back)) {
            paneSignIn.toFront();
    } 
    if(event.getSource().equals(back1)) {
            paneSignUp.toFront();
    } 
    
    if(event.getSource().equals(back2)) {
            welcome.toFront();
    } 
} 

public void seConnect(ActionEvent actionEvent) throws IOException {
       
        if (!utilisateur.getText().equals("") && !pwd.getText().equals("")) {
            Object res[] = encadrantFacade.seConnecter(utilisateur.getText(), pwd.getText());
            int resInt = (int) res[0];
            if (resInt == 1){
                Encadrant e = (Encadrant) res[1];
                Session.clear();
                Session.setAttribut(e, "encaConnect");
             
               Acceuil.forward(actionEvent, "EditeProfile.fxml", this.getClass());
            } else if (resInt == -1) {
                alertLogin(actionEvent);
            } else if (resInt == -2) {
                alertPasswd(actionEvent);
                pwd.setText("");

            } else if (resInt == -3) {
                alertBlock(actionEvent);
                pwd.setText("");
            }
        } else {
            alertChamps(actionEvent);
        }
    }
 private void alertChamps(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs Vide!");
        alert.setContentText("Il est necessaire de remplire tout les champs pour se connecter!");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }

    private void alertPasswd(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mot de passe incorrect!");
        alert.setContentText("");
        alert.setHeaderText("MOT DE PASSE INCORRECT!");
        alert.showAndWait();
    }
    private void alertBlock(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Compte Bloquée!");
        alert.setContentText("Votre Compte est Bloquee!!\n");
        alert.setHeaderText("BLOQUEE!");
        alert.showAndWait();
    }

    private void alertLogin(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Compte non Trouvée! ");
        alert.setContentText("Aucun compte a ce Login!!.. \nSi vous avez pas de compte vous pouvez le crée en appuiant le boutton en bas");
        alert.setHeaderText("DESOLEE!");
        alert.showAndWait();
    }
    
    @FXML
    private void ajout(ActionEvent ae) throws IOException {
        Encadrant encadrant = getParam(ae);
        if (encadrant != null) {
            encadrantFacade.create(encadrant);
            paneSignIn.toFront();
            actualiser();
        }
    }

   
 private void alertChampsVide(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Quelque Champs sont Vide!");
        alert.setContentText("Assurez-vouz qu'aucun champs est vide!! ");
        alert.setHeaderText("ATTENTION!");
        alert.showAndWait();
    }
  private void alertLoginExist(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de Login");
        alert.setContentText("Ce Login existe Déja... Essayer un autre!");
        alert.setHeaderText("CHANGER CE LOGIN!");
        alert.showAndWait();
    }
   private void initComboBox() {
        departement.setItems(FXCollections.observableArrayList(departementFacade.findAll()));        
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
        departement.setValue(null);
        stagee.setValue(null);
    }

    private Encadrant getParam(ActionEvent ae) {
        
    String gender = "";
        if (male.isSelected()) {
            gender = "M";
        } else if (female.isSelected()) {
            gender = "F";
        }

        if (!mail.getText().equals("") && !nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("") && !tel.getText().equals("") && !gender.equals("") && !departement.getValue().equals("")) {
            Encadrant encadrant = new Encadrant(user.getText(), pass.getText(), nom.getText(), prenom.getText(), adresse.getText(), tel.getText(), mail.getText(), gender, departement.getValue());
            encadrant.setBlocked(0);
            encadrant.setRoot(0);
            return encadrant;
        } else {
            alertChampsVide(ae);
            return null;
        }
    }
 
    
    
}
