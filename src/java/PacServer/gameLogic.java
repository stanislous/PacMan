package PacServer;

import java.util.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author E/13/377 & E/13/270
 */
public final class gameLogic {

    HashMap<String, Dots> foodsMap;                      //Two hashMaps for foods and 4 players
    HashMap<String, Players> playersMap;
    
    final static int number_of_dots = 20;                //Number of Foods
    static int count = 0;

    public gameLogic() {

        foodsMap = new HashMap<>();
        playersMap = new HashMap<>();

        this.insertFood();              //insert foods and player to the board whenever a player
        this.insertPlayers();           //comes to play the game.               
    }

    Random rand = new Random();

    void keyStroke(String keyStroke, String player) {
        int key = Integer.parseInt(keyStroke);
            updatePlayerPosition(key, player);
            eatFoods(player);
            playerColide(player);
    }

    void insertFood() {        //insert food items randomly and put them into hashmap

        for (int i = 0; i < number_of_dots; i++) {
            Dots dot = new Dots(Color(), rand.nextInt(44), rand.nextInt(44));
            foodsMap.put(Integer.toString(dot.x) + "|" + Integer.toString(dot.y), dot);     //given a unique key for all foods
        }
    }

    void insertPlayers() {      //make 4 players and put them into hashmap

        Players player1 = new Players(players(1), 0, 0, 0);        //give them initial a position
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

    void updatePlayerPosition(int key, String player) {                     //Update the current player position

        for (String k : playersMap.keySet()) {
            Players tmp = playersMap.get(player);

            if (k.equals(player)) {
                if (key == 37 && tmp.x != 0) {   //Left movement
                    tmp.x -= 1;
                } else if (key == 37 && tmp.x == 0) {
                    tmp.x = 44;
                } else if (key == 39 && tmp.x != 44) { //Right movement
                    tmp.x += 1;
                } else if (key == 39 && tmp.x == 44) {
                    tmp.x = 0;
                } else if (key == 38 && tmp.y != 0) {  //Up movement
                    tmp.y -= 1;
                } else if (key == 38 && tmp.y == 0) {
                    tmp.y = 44;
                } else if (key == 40 && tmp.y != 44) {  //Down movement
                    tmp.y += 1;
                } else if (key == 40 && tmp.y == 44) {
                    tmp.y = 0;
                }
                playersMap.put(player, tmp);
            }
        }

    }

    void eatFoods(String player) {                                        //Remove foods that is eaten and update score board

        for (String k : playersMap.keySet()) {
            Players tmp = playersMap.get(player);

            if (k.equals(player)) {
                for (String i : foodsMap.keySet()) {
                    Dots dot = foodsMap.get(i);
                    if (tmp.x == dot.x && tmp.y == dot.y) {
                        if (null == dot.color) {
                            tmp.score += 5;
                        } else {
                            count++;
                            switch (dot.color) {
                                case "R":
                                    tmp.score += 1;
                                    break;
                                case "G":
                                    tmp.score += 2;
                                    break;
                                default:
                                    tmp.score += 4;
                                    break;
                            }
                        }
                        playersMap.put(player, tmp);
                        foodsMap.remove(i);            //remove foods if it is eaten.
                    }

                }
            }
        }
        if (count == number_of_dots) {                 //if all foods are eaten, check who is winner
            Winner();
        }
    }

    void playerColide(String player) {                          //Find weather there is a collide
        Players tmp2 = playersMap.get(player);

        for (String k : playersMap.keySet()) {
            if (playersMap.get(k) != playersMap.get(player)) {
                Players tmp1 = playersMap.get(k);

                if (tmp1.x == tmp2.x && tmp1.y == tmp2.y) {    //if there is a collide, reset the particular players
                    if (tmp2.player == "P1") {                 //and reduce score.
                        tmp2.x = 0;
                        tmp2.y = 0;
                        tmp2.score -= 3;
                    } else if (tmp2.player == "P2") {
                        tmp2.x = 44;
                        tmp2.y = 0;
                        tmp2.score -= 3;
                    } else if (tmp2.player == "P3") {
                        tmp2.x = 0;
                        tmp2.y = 44;
                        tmp2.score -= 3;
                    } else if (tmp2.player == "P4") {
                        tmp2.x = 44;
                        tmp2.y = 44;
                        tmp2.score -= 3;
                    }

                    playersMap.put(player, tmp2);

                    if (k == "P1") {
                        tmp1.x = 0;
                        tmp1.y = 0;
                        tmp1.score -= 3;
                    } else if (k == "P2") {
                        tmp1.x = 44;
                        tmp1.y = 0;
                        tmp1.score -= 3;
                    } else if (k == "P3") {
                        tmp1.x = 0;
                        tmp1.y = 44;
                        tmp1.score -= 3;
                    } else if (k == "P4") {
                        tmp1.x = 44;
                        tmp1.y = 44;
                        tmp1.score -= 3;
                    }
                    playersMap.put(k, tmp1);
                }

            }
        }
    }

    void Winner() {                          //Find who is the winner
        int highest_score = 0;
        String winner = "";
        for (String k : playersMap.keySet()) {
            if (playersMap.get(k).score > highest_score) {
                highest_score = playersMap.get(k).score;
                winner = playersMap.get(k).player;
            }
        }
        System.out.println(winner + " is the winner.");
        
        //return winner;
    }

}
