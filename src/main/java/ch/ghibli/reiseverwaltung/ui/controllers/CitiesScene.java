package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.City;
import ch.ghibli.reiseverwaltung.backend.Country;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.backend.utils.ObjectNotFoundException;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This class is a controller for the ShowScene
 * It displays all cities
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CitiesScene extends ControllerBase{
    private Country country;
    public Label name;
    public VBox vBox;

    /**
     * This function initializes the scene and loads all the data needed
     * @param country used to return to the previous scene and load certain data
     */
    @Override
    public void init(Country country) {
        this.country = country;
        name.setText("Städte Verwalten");
        load();
    }

    /**
     * This function creates prefabs from the data and adds them to the vbox
     */
    private void load(){
        for (City city : country.cities) {
            AnchorPane anchorPane = UiManager.getCityPrefab(city);
            if (anchorPane != null) {
                ((Button) anchorPane.getChildren().get(1)).setOnAction(this::delete);
                ((Button) anchorPane.getChildren().get(2)).setOnAction(this::edit);
                vBox.getChildren().add(anchorPane);
            }
        }
    }

    public void back(){
        UiManager.showCountriesScene();
    }

    public void create(){
        UiManager.createCitiesScene(null,country);
    }

    public void delete(ActionEvent event){
        try {
            Data.removeCity(country,Data.getCityById(((Button)event.getSource()).getId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        vBox.getChildren().clear();
        load();
    }

    public void edit(ActionEvent event){
        try {
            UiManager.createCitiesScene(Data.getCityById(((Button)event.getSource()).getId()), country);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
