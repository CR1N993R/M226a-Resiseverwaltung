package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;

public class Locality extends Indexable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
