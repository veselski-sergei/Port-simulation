package service3;

import service1.Ship;
import service1.Cargo;
import service2.JsonReader;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;

public class Handler {
    private ArrayDeque<Ship> shipQueue;
    final int LATENCY = 1440;
    private static int total;

    public void handle(List<Ship> list){
        Random random = new Random();
        for (Cargo type : Cargo.values()) {
            shipQueue = getQueue(list, type);
            for (Ship ship: shipQueue ) {
                HandlerCrane crane = new HandlerCrane(ship.getType());
                int timeOfHandling = (int) (random.nextInt(LATENCY) + (ship.getWeight() / crane.getProductivity()));
                printInfo(ship, timeOfHandling);
            }
        }
    }

    public ArrayDeque<Ship> getQueue(List<Ship> list, Cargo type){
        ArrayDeque<Ship> tempQueue = new ArrayDeque<>();
        for(Ship ship: list){
            if(ship.getType() == type){
                tempQueue.add(ship);
            }
        }
        return tempQueue;
    }

    public void printInfo(Ship ship, int timeOfHandling) {
        System.out.println("\n-------------------------" + ++total +"----------------------------\n" +
                ship + "\nTime of handling: " + timeOfHandling + " minutes" +
                "\n-------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        JsonReader reader = new JsonReader();
        List<Ship> schedule = reader.readFromJson("src\\\\resources\\\\ships.json");

        Handler handling = new Handler();
        handling.handle(schedule);
    }
}
