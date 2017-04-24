package PacServer;

import javax.json.Json;
import java.util.Random;
import javax.json.JsonArrayBuilder;

class Dots {

    Random rand = new Random();
    int x;
    int y;
    String color;

    Dots(String color, int x, int y) {

        this.x = x;
        this.y = y;
        this.color = color;

    }

    private String Color() {
        int color = rand.nextInt(3);
        switch (color) {
            case 0:
                return "R";
            case 1:
                return "G";
            default:
                return "B";
        }
    }

    JsonArrayBuilder DotFormat() {

        JsonArrayBuilder player_list = Json.createArrayBuilder();

        for (int i = 0; i < 12; i++) {

            player_list.add(Json.createArrayBuilder()
                    .add(Color())
                    .add(rand.nextInt(44))
                    .add(rand.nextInt(44)).build());
        }
        return player_list;
    }
    // @Override
    // public String toString(){
    //return Integer.toString()+"+"+Integer.toString(dots.y);

    // }
}
