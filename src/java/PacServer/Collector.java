package PacServer;

import java.util.Random;
import org.json.simple.JSONObject;

public class Collector {
    
    Random rand = new Random();
    JSONObject obj = new JSONObject();
    
    Collector(){ 
        
    Dots newDot1 = new Dots("R",rand.nextInt(44),rand.nextInt(44));
    Dots newDot2 = new Dots("G",rand.nextInt(44),rand.nextInt(44));
    Dots newDot3 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    Dots newDot4 = new Dots("R",rand.nextInt(44),rand.nextInt(44));
    Dots newDot5 = new Dots("G",rand.nextInt(44),rand.nextInt(44));
    Dots newDot6 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    Dots newDot7 = new Dots("R",rand.nextInt(44),rand.nextInt(44));
    Dots newDot8 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    Dots newDot9 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    Dots newDot10 = new Dots("R",rand.nextInt(44),rand.nextInt(44));
    Dots newDot11 = new Dots("G",rand.nextInt(44),rand.nextInt(44));
    Dots newDot12 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
   
    Players player1 = new Players("P1",5,0,0);
    Players player2 = new Players("P2",6,0,44);
    Players player3 = new Players("P3",2,44,0);
    Players player4 = new Players("P4",8,44,44);
    }
    
    public void DotFormet(){
    
    }
    
    public void PlayerFormat(){
    
    }
}
