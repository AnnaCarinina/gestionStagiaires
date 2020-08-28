/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import helper.EncadrantFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import service.DepartementFacade;
import service.EncadrantFacade;
import service.StageeFacade;
import service.StagiaireFacade;


/**
 * FXML Controller class
 *
 * @author LEILA
 */
public class WelcomeController implements Initializable {


    
    @FXML
    private Button encadrant;
    
    @FXML
    private Button responsable;
    

    
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
      
    }    






    @FXML
    private void handleActionButton(ActionEvent event) throws IOException{
        
        if(event.getSource().equals(encadrant)){
            Acceuil.forward(event, "LoginEnca.fxml", this.getClass());
        }
        
        if(event.getSource().equals(responsable)){
             Acceuil.forward(event, "LoginRespo.fxml", this.getClass());
        }
    }
    
}
