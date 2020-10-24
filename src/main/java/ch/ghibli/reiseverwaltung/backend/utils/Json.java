package ch.ghibli.reiseverwaltung.backend.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Json {
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

    public static JSONObject loadFromJSON(String path) {
        try {
            FileReader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);
        }catch (IOException | ParseException ignored){ }
        return null;
    }

    public static void deleteRecursive(File fileToDelete){
        File[]content = fileToDelete.listFiles();
        if (content!=null) {
            for (File file : content) {
                deleteRecursive(file);
            }
        }
        fileToDelete.delete();
    }

    public static void checkName(File file){
        if (file.exists()){
            file.delete();
        }
    }

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
