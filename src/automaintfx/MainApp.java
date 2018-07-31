/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaintfx;

import automaintfx.model.Vehicle;
import automaintfx.view.LoginWindowController;
import automaintfx.view.VehicleEditDialogController;
import automaintfx.view.VehicleOverviewController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private String users;
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
    
    /**
     * 
     * Constructor
     * 
     */
    public MainApp(){
        //sample vehicle data goes here
        vehicleData.add(new Vehicle("ABCD1", "Ford", "Escort", 1999));
        vehicleData.add(new Vehicle("ABCD2", "Ford", "Explorer", 2000));
        vehicleData.add(new Vehicle("ABCD3", "Ford", "Expedition", 2001));
        
        users = "Brian";
        System.out.println(users.toString()); //debugging
    }
    
   /**
    * Returns the data as an observable list of vehicle
    * @return vehicleData
    */
    
    public ObservableList<Vehicle> getVehicleData(){
        return vehicleData;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Auto Maintenance Application");
        
        initRootLayout(); //Sets up main application window
        showLoginWindow(); //Shows the login window
        
        showVehicleOverview();//shows main vehicle list
        
    }
    
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            //shows the scene
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showVehicleOverview(){
        try {
            //Load the overview into the FXML Loader object
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VehicleOverview.fxml"));
            AnchorPane vehicleOverview = (AnchorPane) loader.load();
            //Set Vehicle overview into the center of the rootlayout
            rootLayout.setCenter(vehicleOverview);
            
            //Creates a VehicleOverView controller obj and assigns the FXMLs controller obj to it
            VehicleOverviewController controller = loader.getController();

            /**
             * Calls the setMainApp method, and passes the MainApp object
             * Gives the controller access to the public methods and fields 
             * of the MainApp object
            */
            controller.setMainApp(this);            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showVehicleEditDialog(Vehicle vehicle){
        try {
            //Load the fxml file and create a new stage for the popup window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VehicleEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            //Create the dialog Stage
            Stage dialogStage = new Stage(); //create new stage object for the window
            dialogStage.setTitle("Edit Vehicle"); //set title of window
            dialogStage.initModality(Modality.WINDOW_MODAL); //?
            dialogStage.initOwner(primaryStage); //owner of this window is the primary stage
            Scene scene = new Scene(page); //create a new scene for the stage using page obj
            dialogStage.setScene(scene); //pass the scene to the stage obj
            
            //Pass along the vehicle to edit to the controller
            VehicleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVehicle(vehicle);
            
            //Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked(); //returns true if the user clicked OK, false otherwise
                
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public String getUsers(){
        return users;
    }
    
    public void showLoginWindow(){
        try {
            //Load the fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginWindow.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            loginStage.setScene(scene);
            
            LoginWindowController controller = loader.getController();
            controller.setLoginStage(loginStage);
            
            loginStage.showAndWait();
            primaryStage.show();
            
        } catch (IOException e) {
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
