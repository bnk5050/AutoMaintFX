/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaintfx.view;

import automaintfx.model.Vehicle;
import automaintfx.MainApp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * FXML Controller class
 *
 * @author Brian
 */
public class VehicleOverviewController {

    //Table controls
    @FXML
    private  TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> makeColumn;
    @FXML
    private TableColumn<Vehicle, String> modelColumn;
    
    //Label controls
    @FXML
    private Label makeLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label VINLabel;
    @FXML
    private Label yearLabel;
    
    //Reference variable to the main application
    private MainApp mainApp;
    
    //Empty Constructor
    public VehicleOverviewController(){
    }
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        //Initialize the vehicle table with the two columns
        makeColumn.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        
        //Passes null to method which clears any details that would show in details pane
        showVehicleDetails(null);
        
        /**
         * Listen for selectio nchanges and show the vehicle details when changed.
         * newValue is passed to the showPersonDetails method
         */
        
        vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVehicleDetails(newValue));
        
    }    
    
    /**
     * This method is called by the main application to give a reference back to iteself
     * 
     * @param mainApp
     */
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        
        //Add observable list data to the table by reaching to MainApp
        vehicleTable.setItems(mainApp.getVehicleData());
    }
    
    /**Fills the text fields to show details about the vehicle
     * If the vehicle passed is null, all the labels/fields are cleared.
     * 
     * @param vehicle the vehicle or null
     */
    
    private void showVehicleDetails(Vehicle vehicle){
        if (vehicle != null){
            //Fill lables with info from the vehicle obj passed to this method
            VINLabel.setText(vehicle.getVIN());
            makeLabel.setText(vehicle.getMake());
            modelLabel.setText(vehicle.getModel());
            yearLabel.setText(Integer.toString(vehicle.getYear()));
        }
        else{
            //Vehicle passed is null, so remove text from labels
            VINLabel.setText("");
            makeLabel.setText("");
            modelLabel.setText("");
            yearLabel.setText("");
        }
    }
    
    /**
     * Called when a user clicks on the delete button
     */
    
    @FXML
    private void handleDeleteVehicle(){
        int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
        //Checks to see if the selected index of the list is > -1 (something selected)
        if (selectedIndex >=0){
            vehicleTable.getItems().remove(selectedIndex);
        }
        else{
            //Nothing is selected, so show an alert window
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Vehicle Selected");
            alert.setContentText("Please select a person in the table");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewVehicle(){
        Vehicle tempVehicle = new Vehicle(); //creates a temporary new vehicle
        //calls the edit dialog box and sets the results to okClicked.  If true, its saved
        boolean okClicked = mainApp.showVehicleEditDialog(tempVehicle);
        //check to see if the clicked OK to save, then save the vehicle
        if (okClicked){
            mainApp.getVehicleData().add(tempVehicle);
        }
    }
    
    @FXML
    private void handleEditVehicle(){
        //find out whcih person is selected and assign to the selectedPerson obj
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null){
            boolean okClicked = mainApp.showVehicleEditDialog(selectedVehicle);
            if (okClicked){
                showVehicleDetails(selectedVehicle);
            }
        }
        else{
            //Nothing selected, warn the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No vehicle selected");
            alert.setContentText("Please select a vehicle in the table");
            
            //wait for the user to acknowledge the alert
            alert.showAndWait();
            
        }
    }
    
    
}
