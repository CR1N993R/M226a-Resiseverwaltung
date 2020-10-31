package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.Customer;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.backend.Trip;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class is for displaying all Trips and managing them
 * This class is also a Controller for the ShowScene
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class TripsScene extends ControllerBase{
    private Customer customer;
    public Label name;
    public VBox vBox;

    /**
     * Initializes the class and loads all the data
     * @param customer Provides the customer needed to load the data
     */
    @Override
    public void init(Customer customer) {
        this.customer = customer;
        name.setText("Reisen Verwalten");
        load();
    }

    /**
     * Creates and puts all prefabs into the VBox
     */
    private void load(){
        for (Trip trip : customer.trips) {
            AnchorPane anchorPane = UiManager.getTripPrefab(trip);
            if (anchorPane != null) {
                ((Button)anchorPane.getChildren().get(3)).setOnAction(this::delete);
                ((Button)anchorPane.getChildren().get(4)).setOnAction(this::edit);
                vBox.getChildren().add(anchorPane);
            }
        }
    }

    /**
     * Returns to the Customers scene
     */
    public void back(){
        UiManager.showCustomersScene();
    }

    /**
     * Loads the create scene
     */
    public void create(){
        UiManager.createTripScene(null,customer);
    }

    /**
     * Handles the delete button
     * @param event Provides the event source
     */
    public void delete(ActionEvent event){
        try {
            Data.removeTrip(customer, Data.getTripById(((Button)event.getSource()).getId()));
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
            UiManager.createTripScene(Data.getTripById(((Button)event.getSource()).getId()), customer);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
