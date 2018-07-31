/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaintfx.view;

import java.util.Arrays;
import automaintfx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class LoginWindowController {

    @FXML 
    private TextField usernameField;
    
    private boolean loginAuth;
    private Stage loginStage;
    private MainApp mainApp;
    private String usersString = "Brian";
    //empty constructor
    public LoginWindowController(){
    }
    
    public void initialize() {
        // TODO
    }    
    
    public void setLoginStage(Stage loginStage){
        this.loginStage = loginStage;
    }
    
    public void setMainApp (MainApp mainApp){
        this.mainApp = mainApp;

    }
    
    @FXML 
    public void handleOK(){
        System.out.println(usersString);
        if (usersString.equals(usernameField.getText())){
                loginAuth = true;
                loginStage.close();
                
            }
            else{
                loginAuth = false;
            }
        if (loginAuth = false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("User not recognized");
            alert.setContentText(usernameField.getText()+" is not recognzied");
            alert.showAndWait();
        }
        
    }
    
    public boolean isAuthenticated(){
        return loginAuth;
    }
    
}
