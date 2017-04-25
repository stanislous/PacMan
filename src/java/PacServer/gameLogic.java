package PacServer;

import java.util.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author stanislous
 */
public class gameLogic {

    //Players players;
    //Dots dots;
    HashMap<String, Dots> foodsMap;
    HashMap<String, Players> playersMap;
    final static int v = 40;

    public gameLogic() {

        foodsMap = new HashMap<>();
        playersMap = new HashMap<>();

        this.insertFood();
        this.insertPlayers();
    }

    Random rand = new Random();

    Collector collector = new Collector();

    void keyStroke(String keyStroke) {
        int key = Integer.parseInt(keyStroke);
        updatePlayerPosition(key);
        eatFoods();
    }

    void insertFood() {        //insert food items randomly and put them into hashmap

        for (int i = 0; i < v; i++) {
            Dots dot = new Dots(Color(), rand.nextInt(44), rand.nextInt(44));
            foodsMap.put(Integer.toString(dot.x) + "|" + Integer.toString(dot.y), dot);
        }
    }

    void insertPlayers() {      //make 4 players and put them into hashmap

        Players player1 = new Players(players(1), 0, 0, 0);
        playersMap.put(player1.player, player1);
        Players player2 = new Players(players(2), 0, 44, 0);
        playersMap.put(player2.player, player2);
        Players player3 = new Players(players(3), 0, 0, 44);
        playersMap.put(player3.player, player3);
        Players player4 = new Players(players(4), 0, 44, 44);
        playersMap.put(player4.player, player4);

    }

    JsonArrayBuilder comleteDotArray() {       //json array for foods

        JsonArrayBuilder dotArray = Json.createArrayBuilder();
        for (String key : foodsMap.keySet()) {
            dotArray.add(foodsMap.get(key).DotFormat());
        }
        return dotArray;
    }

    JsonArrayBuilder comletePlayerArray() {         //json array for players

        JsonArrayBuilder playerArray = Json.createArrayBuilder();
        for (String key : playersMap.keySet()) {
            playerArray.add(playersMap.get(key).PlayerFormat());
        }
        return playerArray;
    }

    JsonObject completeJsonObject() {           //concatenate player and dots json formats
        JsonObject obj = Json.createObjectBuilder()
                .add("DOTS", comleteDotArray())
                .add("PLAYERS", comletePlayerArray()).build();
        return obj;
    }

    private String Color() {                  //Assign colors to the dots
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

    private String players(int i) {          //Assign names to the players
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

    void updatePlayerPosition(int key) {                     //Update the current player position

        for (String k : playersMap.keySet()) {
            Players tmp = playersMap.get("P1");

            if (k.equals("P1")) {
                if (key == 37 && tmp.x != 0) {   //Left
                    tmp.x -= 1;
                } else if (key == 37 && tmp.x == 0) {
                    tmp.x = 44;
                } else if (key == 39 && tmp.x != 44) { //Right
                    tmp.x += 1;
                } else if (key == 39 && tmp.x == 44) {
                    tmp.x = 0;
                } else if (key == 38 && tmp.y != 0) {  //Up
                    tmp.y -= 1;
                } else if (key == 38 && tmp.y == 0) {
                    tmp.y = 44;
                } else if (key == 40 && tmp.y != 44) {  //Down
                    tmp.y += 1;
                } else if (key == 40 && tmp.y == 44) {
                    tmp.y = 0;
                }
                playersMap.put("P1", tmp);
            }
        }

    }

    Players getPlayer() {
        Players ret = null;
        for (String k : playersMap.keySet()) {
            if (!playersMap.get(k).assigned) {
                playersMap.get(k).assigned = true;
                ret = playersMap.get(k);
            }
        }
        return ret;
    }

    void eatFoods() {                                        //Remove foods that is eaten and update score board

        for (String k : playersMap.keySet()) {
            Players tmp = playersMap.get("P1");

            if (k.equals("P1")) {
                for (String i : foodsMap.keySet()) {
                    Dots dot = foodsMap.get(i);
                    if (tmp.x == dot.x && tmp.y == dot.y) {
                        if (dot.color == "R") {
                            tmp.score += 1;
                        } else if (dot.color == "G") {
                            tmp.score += 2;
                        } else {
                            tmp.score += 5;
                        }
                        playersMap.put("P1", tmp);
                        foodsMap.remove(i);
                    }

                }
            }
        }
    }

//    public static void main(String argc[]) {
//
//        gameLogic collector = new gameLogic();
//        collector.insertFood();
//        collector.insertPlayers();
//        System.out.println("==============================================================================");
//        System.out.println(collector.keyStroke(keyStroke));
//    }
}
