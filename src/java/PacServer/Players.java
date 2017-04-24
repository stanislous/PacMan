package PacServer;

import java.util.Random;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

class Players {

    Random rand = new Random();

    int x;
    int y;
    int score;
    String player;

    Players(String player, int score, int x, int y) {

        this.x = rand.nextInt(44);
        this.y = rand.nextInt(44);
        this.player = player(rand.nextInt(3));
        this.score = 0;
    }

    private String player(int i) {
        switch (i) {
            case 1:
                return "P1";
            case 2:
                return "P2";
            case 3:
                return "P3";
            default:
                return "P4";
        }
    }

    JsonArrayBuilder PlayerFormat() {

        JsonArrayBuilder dot_list = Json.createArrayBuilder();

        dot_list.add(Json.createArrayBuilder()
                .add(player(1))
                .add(rand.nextInt(10))
                .add(0)
                .add(0).build());
        dot_list.add(Json.createArrayBuilder()
                .add(player(2))
                .add(rand.nextInt(10))
                .add(0)
                .add(44).build());
        dot_list.add(Json.createArrayBuilder()
                .add(player(3))
                .add(rand.nextInt(10))
                .add(44)
                .add(0).build());
        dot_list.add(Json.createArrayBuilder()
                .add(player(4))
                .add(rand.nextInt(10))
                .add(44)
                .add(44).build());

        return dot_list;

    }

}
