package PacServer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

class Players {

    int x;
    int y;
    int score;
    String player;

    Players(String player, int score, int x, int y) {     //assign attributes for players

        this.x = x;
        this.y = y;
        this.player = player;
        this.score = score;

    }
   
    JsonArray PlayerFormat() {                     //format player objects to json objects

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        arrayBuilder.add(this.player);
        arrayBuilder.add(this.score);
        arrayBuilder.add(this.x);
        arrayBuilder.add(this.y);

        return arrayBuilder.build();
        
    }

}