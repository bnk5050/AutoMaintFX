/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaintfx.view;

import automaintfx.model.Vehicle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class VehicleEditDialogController {

    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField VINField;
    @FXML
    private TextField yearField;
    
    private Stage dialogStage; //creates a stage obj named dialogStage
    private Vehicle vehicle; //creates a vehicle obj named vehicle
    private boolean okClicked = false;
    
        /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }    
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    /**Returns true if the user clicked OK, false otherwise
     * 
     * @return
     */
    
    /**
     * Sets the vehicle to be edited in the dialog.
     * Receives a vehicle obj and names it vehicle
     * @return 
     */
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        makeField.setText(vehicle.getMake());
        modelField.setText(vehicle.getModel());
        VINField.setText(vehicle.getVIN());
        yearField.setText(Integer.toString(vehicle.getYear()));
    }
    
    public boolean isOkClicked(){
        return okClicked;
    }
    
    /**Called when the user clicks OK
     * 
     */
    
    @FXML
    private void handleOk(){
        if(isInputValid()){
            //Sets the vehicle info by getting the text from each textfield
            vehicle.setMake(makeField.getText());
            vehicle.setModel(modelField.getText());
            vehicle.setVIN(VINField.getText());
            vehicle.setYear(Integer.parseInt(yearField.getText()));
            
            okClicked = true;
            dialogStage.close();
        }
        
    }
    
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
    private boolean isInputValid(){
        String errorMessage = ""; //Creates an errorMessage obj and sets it to null
        //If the make text fiel is null or has 0 characters, set an error message
        if (makeField.getText() == null || makeField.getText().length() == 0) {
            errorMessage += "Not a valid make!\n";
        }
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "Not a valid model!\n";
        }
        if (VINField.getText() == null || VINField.getText().length() == 0) {
            errorMessage += "Not a valid VIN!\n";
        }    
        if (yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage += "Not a valid make!\n";
        }
        else{
            //try to parse the postal code into an int
            try {
                Integer.parseInt(yearField.getText());
            } catch (Exception e) {
                errorMessage += "Not a valid year (must be an integer)!\n";
            }
        }
        
        if (errorMessage.length() == 0){
            return true;
        }
        else{
            //show the error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
                    
            return false;
        }
    }
}

