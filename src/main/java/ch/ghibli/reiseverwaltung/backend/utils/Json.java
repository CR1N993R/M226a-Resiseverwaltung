package ch.ghibli.reiseverwaltung.backend.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is for displaying all Trips and managing them
 *
 * @author Cedric Ringger
 * @author Miki Ujihara
 * @version 1.0
 */
public abstract class Json {
    /**
     * Saves the given object to JSON
     * @param object the object to be saved
     * @param path the path to save it
     */
    public static void saveToJson(JSONObject object, String path){
        try {
            checkPath(new File(path));
            checkName(new File(path));
            FileWriter writer = new FileWriter(path);
            writer.write(object.toJSONString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSONObject from a file
     * @param path Location of the file
     * @return Returns the created JSONObject
     */
    public static JSONObject loadFromJSON(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);
        }catch (IOException | ParseException ignored){ }
        return null;
    }

    /**
     * Deletes the specified folder recursive
     * @param fileToDelete path to delete
     */
    public static void deleteRecursive(File fileToDelete){
        File[]content = fileToDelete.listFiles();
        if (content!=null) {
            for (File file : content) {
                deleteRecursive(file);
            }
        }
        fileToDelete.delete();
    }

    /**
     * Removes file if already exists
     * @param file path to file
     */
    private static void checkName(File file){
        if (file.exists()){
            file.delete();
        }
    }

    /**
     * Creates all folders till file specified
     * @param file Path to file
     */
    public static void checkPath(File file){
        String path = "";
        for (String s : file.getPath().split("\\\\")) {
            path += s + "\\";
            File file1 = new File(path);
            if (!file1.exists() && !path.contains(".")){
                file1.mkdir();
            }
        }
    }
}
