package PacServer;

import java.util.Random;
import org.json.simple.*;
import java.util.ArrayList;

public class Collector {
     
    Random rand = new Random();
    static JSONObject obj = new JSONObject();
    ArrayList<Dots> dot_list=new ArrayList<Dots>();
    ArrayList<Players> player_list=new ArrayList<Players>();
    
    
    Collector(){ 
        
        for (int i = 0; i < 12; i++) {
            dot_list.add(new Dots(Color(), rand.nextInt(44), rand.nextInt(44)));
        }
        
        for (int i = 1; i <= 4; i++) {
            player_list.add(new Players(player(i), i, rand.nextInt(44), rand.nextInt(44)));
            
        }

    }
    
    public String Color(){
        int color = rand.nextInt(2);
        if (color == 0) {
            return "R";
        }else if (color == 1) {
            return "G";
        }else
            return "B";   
    }
    public String player(int i){
        if (i==1) {
            return "P1";
        }else if (i==2) {
            return "P2";
        }else if (i==3) {
            return "P3";
        }else
            return "P4";
    }
    
    
    public void DotFormat(){
        
        ArrayList<JSONArray> array_list=new ArrayList<JSONArray>();
        JSONArray main_array = new JSONArray();
        
        for (int i = 0; i < 12; i++) {
            array_list.add(new JSONArray());
            array_list.get(i).add(dot_list.get(i).color);
            array_list.get(i).add(dot_list.get(i).x);
            array_list.get(i).add(dot_list.get(i).y);
            main_array.add(array_list.get(i));
        }
      
        obj.put("DOTS", main_array);
        //System.out.print(obj.toString());
        
       
    }
    
    public void PlayerFormat(){
        
        ArrayList<JSONArray> array_list=new ArrayList<JSONArray>();
        JSONArray main_array = new JSONArray();
        
        for (int i = 0; i < 4; i++) {
            array_list.add(new JSONArray());
            array_list.get(i).add(player_list.get(i).player);
            array_list.get(i).add(player_list.get(i).score);
            array_list.get(i).add(player_list.get(i).x);
            array_list.get(i).add(player_list.get(i).y);
            main_array.add(array_list.get(i));
        }
     
        obj.put("PLAYERS", main_array);
        
    }

//    public static void main(String argc[]){
//        
//        Collector collector = new Collector();
//        collector.DotFormat();
//        collector.PlayerFormat();
//    }
}
