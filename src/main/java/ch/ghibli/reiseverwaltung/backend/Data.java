package ch.ghibli.reiseverwaltung.backend;

import java.util.ArrayList;

public abstract class Data {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Country> countries = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static void save(){
        
    }

    public static void createFromJson(){

    }
}
