package PacServer;

import java.math.BigDecimal;
import java.util.Random;
import javax.json.*;
import java.util.ArrayList;

public class Collector {
     
    Random rand = new Random();
    JsonObjectBuilder obj = Json.createObjectBuilder();
    
    JsonArrayBuilder dot_list = Json.createArrayBuilder();
   
    //ArrayList<Dots> dot_list;
    //ArrayList<Players> player_list;
    //JsonObjectBuilder obj = Json.createObjectBuilder();
    //JsonArrayBuilder dot_list = Json.createArrayBuilder();
    //Dots dots = new Dots(Color(), rand.nextInt(44), rand.nextInt(44));
    
    Collector(){ 
        
        
      
        
         
         
         
//        for (int i = 1; i <= 4; i++) {
//            player_list.add(new Players(player(i), i, rand.nextInt(44), rand.nextInt(44)));
//    }
}
    
    private String Color(){
        int color = rand.nextInt(2);
        if (color == 0) {
            return "R";
        }else if (color == 1) {
            return "G";
        }else
            return "B";   
    }
    private String player(int i){
        if (i==1) {
            return "P1";
        }else if (i==2) {
            return "P2";
        }else if (i==3) {
            return "P3";
        }else
            return "P4";
    }
    
    
//    public String DotFormat(){
//        
//        ArrayList<JsonArray> array_list=new ArrayList<JsonArray>();
//        JsonArray main_array;
//        
//        for (int i = 0; i < 12; i++) {
//            array_list.add(JsonArray);
//            array_list.get(i).add(dot_list.get(i).color);
//            array_list.get(i).add(dot_list.get(i).x);
//            array_list.get(i).add(dot_list.get(i).y);
//            main_array.add(array_list.get(i));
//        }
//      
//        obj.put("DOTS", main_array);
//       
//        //System.out.print(obj.toString()); 
//       return obj.toString();
//    }
    
     JsonObject PlayerFormat(){
        
//        ArrayList<JSONArray> array_list=new ArrayList<JSONArray>();
//        JSONArray main_array = new JSONArray();
//        
//        for (int i = 0; i < 4; i++) {
//            array_list.add(new JSONArray());
//            array_list.get(i).add(player_list.get(i).player);
//            array_list.get(i).add(player_list.get(i).score);
//            array_list.get(i).add(player_list.get(i).x);
//            array_list.get(i).add(player_list.get(i).y);
//            main_array.add(array_list.get(i));
//        }
//     
//        obj.put("PLAYERS", main_array);
//        return obj.toString();
        JsonObject player_list = Json.createObjectBuilder()
                .add("PLAYERS",
                        Json.createArrayBuilder().add("P1")
                        .add(1)
                        .add(rand.nextInt(44))
                        .add(rand.nextInt(44))
                        .build()
                ).build();
    
    
        return player_list;
    }

//    public static void main(String argc[]){
//        
//        Collector collector = new Collector();
//        System.out.println(collector.DotFormat());
//        System.out.println(collector.PlayerFormat());
//    }
}
