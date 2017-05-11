package PacServer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

class Dots {

    int x;
    int y;
    String color;

    Dots(String color, int x, int y) {      //assign attributes for players

        this.x = x;
        this.y = y;
        this.color = color;

    }

    JsonArray DotFormat() {                         //format foods objects to json objects

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        arrayBuilder.add(this.color);
        arrayBuilder.add(this.x);
        arrayBuilder.add(this.y);

        return arrayBuilder.build();
    }

}
