//Vehicle class

package automaintfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Vehicle {

    private StringProperty VIN;
    private StringProperty make;
    private StringProperty model;
    private IntegerProperty year;
    
    /**
     * Default Constructor
     */
    
    public Vehicle(){
        this(null, null, null, 0);
    }

    public Vehicle(String VIN, String make, String model, int year){
        this.VIN = new SimpleStringProperty(VIN);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.year = new SimpleIntegerProperty(year);
    }
    
//    Vehicle(String inVIN, String inMake, String inModel, int inYear) {
//        VIN = inVIN;
//        make = inMake;
//        model = inModel;
//        year = inYear;
    
//    Vehicle(){
//        VIN = "No VIN";
//        make = "No Make";
//        model = "No Model";
//        year = 1900;
//    }

    public String getVIN(){
        return VIN.get();
    }
    
    public void setVIN(String VIN){
        this.VIN.set(VIN);
    }
    
    public StringProperty VINProperty(){
        return VIN;
    }
    
    public String getMake(){
        return make.get();
    }
    
    public void setMake(String make){
        this.make.set(make);
    }
    
    public StringProperty makeProperty(){
        return make;
    }
    public String getModel(){
        return model.get();
    }
    
    public void setModel(String model){
        this.model.set(model);
    }
    
    public StringProperty modelProperty(){
        return model;
    }    
    
    public int getYear(){
        return year.get();
    }

    public void setYear(int year){
        this.year.set(year);
    }

    public IntegerProperty yearProperty(){
        return year;    
    }
}
