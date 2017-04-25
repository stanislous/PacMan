package PacServer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

class Players {

    int x;
    int y;
    int score;
    String player;
    boolean assigned;

    Players(String player, int score, int x, int y) {

        this.x = x;
        this.y = y;
        this.player = player;
        this.score = score;
        this.assigned = false;

    }

//        Players newPlayers = new Players(player, score, x, y);

    
    JsonArray PlayerFormat() {

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        arrayBuilder.add(this.player);
        arrayBuilder.add(this.score);
        arrayBuilder.add(this.x);
        arrayBuilder.add(this.y);

        return arrayBuilder.build();
        
    }

}