/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LEILA
 */
public class MenuRespoController implements Initializable {

    /**
     * Initializes the controller class.
     * 
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }
    
}
