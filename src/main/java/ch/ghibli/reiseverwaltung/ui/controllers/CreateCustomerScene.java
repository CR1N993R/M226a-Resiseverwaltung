package ch.ghibli.reiseverwaltung.ui.controllers;

import ch.ghibli.reiseverwaltung.backend.Customer;
import ch.ghibli.reiseverwaltung.backend.Data;
import ch.ghibli.reiseverwaltung.ui.UiManager;
import javafx.scene.control.TextField;

/**
 * This Class is the Controller for the CreateCustomerScene
 * The scene is managing Customers
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public class CreateCustomerScene extends ControllerBase{
    public TextField surname;
    public TextField name;
    private Customer customer;

    /**
     * This method initializes the class and loads the data into the ui
     * @param customer the customer to be edited
     */
    public void init(Customer customer){
        this.customer = customer;
        if (customer!=null){
            name.setText(customer.lastname);
            surname.setText(customer.name);
        }
    }

    /**
     * This method handles the save button and saves the Customer
     */
    public void save() {
        if (name.getText().length() > 0 && surname.getText().length() > 0){
            if (customer == null){
                customer = new Customer();
                Data.getCustomers().add(customer);
            }
            customer.name = surname.getText();
            customer.lastname = name.getText();
            customer.save();
            back();
        }
    }

    /**
     * Return to the showCustomersScene
     */
    public void back() {
        UiManager.showCustomersScene();
    }
}
