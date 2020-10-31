package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.Customer;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class is for displaying all Customers and managing them
 * This class is also a controller for the ShowScene
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CustomersScene extends ControllerBase{
    public Label name;
    public VBox vBox;

    /**
     * Initializes the scene and loads the data
     */
    @Override
    public void init() {
        name.setText("Kunden Verwalten");
        load();
    }

    /**
     * Creates and puts all prefabs into the VBox
     */
    private void load(){
        for (Customer customer : Data.getCustomers()) {
            AnchorPane anchorPane = UiManager.getCustomerPrefab(customer);
            if (anchorPane != null) {
                ((Button)anchorPane.getChildren().get(2)).setOnAction(this::delete);
                ((Button) anchorPane.getChildren().get(3)).setOnAction(this::edit);
                ((Button) anchorPane.getChildren().get(4)).setOnAction(this::showTrips);
                vBox.getChildren().add(anchorPane);
            }
        }
    }

    /**
     * Returns to the Main scene
     */
    public void back(){
        UiManager.showMainScene();
    }

    /**
     * Loads the create scene
     */
    public void create(){
        UiManager.createCustomerScene(null);
    }

    /**
     * Handles the delete button
     * @param event Provides the event source
     */
    public void delete(ActionEvent event){
        try {
            Data.removeCustomer(Data.getCustomerById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        vBox.getChildren().clear();
        load();
    }

    /**
     * Handles the edit button and loads the edit scene
     * @param event Provides the event source
     */
    public void edit(ActionEvent event){
        try {
            UiManager.createCustomerScene(Data.getCustomerById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the showTrips button and loads the showTripsScene
     * @param event Provides the event source
     */
    public void showTrips(ActionEvent event){
        try {
            UiManager.showTripsScene(Data.getCustomerById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
