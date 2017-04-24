package PacServer;

import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author stanislous
 */
public class gameLogic {

    Players players;
    Dots dots;

    HashMap<String, Dots> Foods;
    HashMap<String, Players> Players;

    Collector collector = new Collector();

    void keyStroke(String keyStroke) {
            
    }

    JsonArrayBuilder comletePlayerArray() {

        JsonArrayBuilder dotArray = Json.createArrayBuilder();

        for (String key : Foods.keySet()) {
            dotArray.add(Foods.get(key).DotFormat());
        }
        return dotArray;
    }

    JsonArrayBuilder complteDotArray() {

        JsonArrayBuilder playerArray = Json.createArrayBuilder();

        for (String key : Players.keySet()) {
            playerArray.add(Players.get(key).PlayerFormat());
        }
        return playerArray;
    }

    JsonObject completeJsonObject() {
        JsonObject obj = Json.createObjectBuilder()
                .add("DOTS", complteDotArray())
                .add("PLAYERS",comletePlayerArray()).build();
        return obj;
    }

}
