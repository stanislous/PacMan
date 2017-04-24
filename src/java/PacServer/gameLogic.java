package PacServer;

import java.util.HashMap;

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

    void intialState() {
        Foods.put(foodKey(), dots);
        Players.put(players.player, players);
    }

}
