package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;

import java.util.ArrayList;

public class Country extends Indexable {
    private String name;
    public ArrayList<Locality> localities;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
