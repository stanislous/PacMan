package PacServer;

import java.util.Random;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

class Players {

    Random rand = new Random();

    int x;
    int y;
    int score;
    String player;

    Players(String player, int score, int x, int y) {

        this.x = x;
        this.y = y;
        this.player = player;
        this.score = score;
    }

    Players newPlayers = new Players(player, score, x, y);

    JsonArray PlayerFormat() {

        return Json.createArrayBuilder()
                .add(this.player)
                .add(this.score)
                .add(this.x)
                .add(this.y).build();
        
    }

}
