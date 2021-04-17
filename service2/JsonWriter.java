package service2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service1.Ship;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public static void writeToJson(List<Ship> list, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(list);
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
