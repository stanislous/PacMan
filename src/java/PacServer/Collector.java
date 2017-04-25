package PacServer;

import java.util.Random;
import javax.json.*;


public class Collector {
     
    Random rand = new Random();
    
    Collector(){}
    
    JsonArrayBuilder PlayerFormat(){
        
        JsonArrayBuilder dot_list = Json.createArrayBuilder();
        
            
            dot_list.add(Json.createArrayBuilder()
                            .add(player(1))
                            .add(rand.nextInt(10))
                            .add(0)
                            .add(0).build());
            dot_list.add(Json.createArrayBuilder()
                            .add(player(2))
                            .add(rand.nextInt(10))
                            .add(0)
                            .add(44).build());
            dot_list.add(Json.createArrayBuilder()
                            .add(player(3))
                            .add(rand.nextInt(10))
                            .add(44)
                            .add(0).build());
            dot_list.add(Json.createArrayBuilder()
                            .add(player(4))
                            .add(rand.nextInt(10))
                            .add(44)
                            .add(44).build());
 
        return dot_list;
    
    }
    
    JsonArrayBuilder DotFormat(){

        JsonArrayBuilder player_list = Json.createArrayBuilder();
        
        for (int i = 0; i < 12; i++) {
            
            player_list.add(Json.createArrayBuilder()
                            .add(Color())
                            .add(rand.nextInt(44))
                            .add(rand.nextInt(44)).build());
            
        }

        return player_list;
    }
    
    JsonObject objectSender(){
        
        JsonObject obj = Json.createObjectBuilder()
                .add("DOTS",DotFormat())
                .add("PLAYERS", PlayerFormat()).build();        
        return obj;
    }

    private String Color(){
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
    private String player(int i){
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
//    public static void main(String argc[]){
//        
//        Collector collector = new Collector();
//        System.out.println(collector.DotFormat());
//        System.out.println(collector.PlayerFormat());
//    }
}