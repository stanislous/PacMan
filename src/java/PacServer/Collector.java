package PacServer;

import java.util.Random;
import org.json.simple.*;

public class Collector {
    
    Random rand = new Random();
    static JSONObject obj = new JSONObject();
    //Collection<JSONObject> items = new ArrayList<JSONObject>();
    
    Dots newDot1,newDot2,newDot3,newDot4,newDot5,newDot6,newDot7,newDot8,newDot9,newDot10,newDot11,newDot12;
   
    Players player1,player2,player3,player4;
    
     Collector(){ 
        
    newDot1 = new Dots("R",rand.nextInt(44),rand.nextInt(44));
    newDot2 = new Dots("G",rand.nextInt(44),rand.nextInt(44));
    newDot3 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot4 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot5 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot6 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot7 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot8 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot9 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot10 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot11 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
    newDot12 = new Dots("B",rand.nextInt(44),rand.nextInt(44));
   
    player1 = new Players("P1",5,0,0);
    player2 = new Players("P2",6,0,44);
    player3 = new Players("P3",2,44,0);
    player4 = new Players("P4",8,44,44);
    }
    
    public void DotFormat(){
        
        JSONArray array1 = new JSONArray();
//      JSONArray array2 = new JSONArray();
        
        array1.add(newDot1.color);
        array1.add(newDot1.x);
        array1.add(newDot1.y);
        
        
        obj.put("DOTS", array1);
//      obj.put("PLAYERS", array2);
        
        System.out.print(obj.toString());
    }
    
    public void PlayerFormat(){
        obj.put("PLAYERS", new JSONArray());
    }

    public static void main(String argc[]){
        
        Collector collector = new Collector();
        collector.DotFormat();
        
    }
}
