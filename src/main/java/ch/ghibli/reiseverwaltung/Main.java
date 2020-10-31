package ch.ghibli.reiseverwaltung;

import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is a travel administration which can administer Customers, Cities, Countries and Trips
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the Program and executes the JavaFX application
     *
     * @param args ApplicationParameter
     */
    public static void main(String[] args) {
        Data.createFromJson();
        launch(args);
    }

    /**
     * Starts the JavaFX application
     *
     * @param stage MainStage for displaying the administration
     */

    @Override
    public void start(Stage stage) {
        UiManager.stage = stage;
        UiManager.showMainScene();
    }
}
