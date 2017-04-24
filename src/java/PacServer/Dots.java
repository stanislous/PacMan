package PacServer;

import javax.json.Json;
import java.util.Random;
import javax.json.JsonArray;
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
    
    Dots newDots = new Dots(color, x, y);

    JsonArray DotFormat() {
 
        return Json.createArrayBuilder()
                    .add(this.color)
                    .add(this.x)
                    .add(this.y).build();
    }
}
