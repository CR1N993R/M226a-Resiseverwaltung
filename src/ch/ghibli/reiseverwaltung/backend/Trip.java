package ch.ghibli.reiseverwaltung.backend;

import ch.ghibli.reiseverwaltung.backend.utils.Indexable;

import java.util.ArrayList;

public class Trip extends Indexable {
    public ArrayList localities;

    public ArrayList getLocalities() {
        return localities;
    }
}
