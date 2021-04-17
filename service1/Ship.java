package service1;

import java.util.Calendar;

public class Ship implements Comparable<Ship>{
    private String name;
    private Cargo type;
    private int weight;
    private Calendar actualTimeOfArrival;
    private Calendar scheduledTimeOfArrival;

    public Ship(String name, Cargo type, int weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
    }

    public Ship(String name, Cargo type, int weight, Calendar actualTimeOfArrival, Calendar scheduledTimeOfArrival) {
        this(name, type, weight);
        this.actualTimeOfArrival = actualTimeOfArrival;
        this.scheduledTimeOfArrival = scheduledTimeOfArrival;
    }

    @Override
    public String toString() {
        return  "Name of ship: " + name +
                "\nCargo type: " + type.name() +
                "\nWeight: " + weight + " tons" +
                "\nScheduled time of arrival: " + scheduledTimeOfArrival.getTime() +
                "\nActual time of arrival: " + actualTimeOfArrival.getTime();
    }

    @Override
    public int compareTo(Ship o) {
        return this.actualTimeOfArrival.compareTo(o.actualTimeOfArrival);
    }

    public Cargo getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Calendar getActualTimeOfArrival() {
        return actualTimeOfArrival;
    }

    public Calendar getScheduledTimeOfArrival() {
        return scheduledTimeOfArrival;
    }
}
