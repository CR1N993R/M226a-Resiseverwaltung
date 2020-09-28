package ch.ghibli.reiseverwaltung.backend.utils;

import java.util.UUID;

public abstract class Indexable {
    protected String id = UUID.randomUUID().toString();
}
