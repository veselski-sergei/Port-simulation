package service2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import service1.Ship;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    public static List<Ship> readFromJson(String fileName) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ship>>() {}.getType();
        try (Reader file = new FileReader(fileName)) {
            return gson.fromJson(file, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
