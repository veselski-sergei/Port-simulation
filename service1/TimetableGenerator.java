package service1;
import service2.JsonWriter;

import java.util.*;

public class TimetableGenerator {
    private List<Ship> timetable;
    private int total;
    private int bulkAmount;
    private int dryAmount;
    private int liquidAmount;
    private String digitName;
    final int WEIGHT = 10000;
    final int LATENCY = 7;

    public TimetableGenerator(int total) {
        this.total = total;
        timetable = new ArrayList<>(this.total);
        Random random = new Random();
        for (int i = 0; i < this.total; ++i) {
            Cargo type = Cargo.values()[random.nextInt(Cargo.values().length)];
            int weight;
            switch (type) {
                case BULK -> {
                    ++bulkAmount;
                    digitName = Integer.toString(bulkAmount);
                    weight = random.nextInt(WEIGHT);
                }
                case LIQUID -> {
                    ++liquidAmount;
                    digitName = Integer.toString(liquidAmount);
                    weight = random.nextInt(WEIGHT);
                }
                case CONTAINER -> {
                    ++dryAmount;
                    digitName = Integer.toString(dryAmount);
                    weight = random.nextInt(WEIGHT);
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
            String name = type.toString() + "-" + digitName;
            Calendar scheduledTimeOfArrival = new GregorianCalendar(2021, Calendar.APRIL,
                    random.nextInt(30) + 1, random.nextInt(24), random.nextInt(60));
            Calendar actualTimeOfArrival = (Calendar) scheduledTimeOfArrival.clone();
            actualTimeOfArrival.add(Calendar.DAY_OF_MONTH, random.nextInt(14) - LATENCY);
            actualTimeOfArrival.add(Calendar.HOUR_OF_DAY, random.nextInt(24));
            actualTimeOfArrival.add(Calendar.MINUTE, random.nextInt(60));
            if(actualTimeOfArrival.get(Calendar.MONTH) != Calendar.APRIL) {
                continue;
            }
            Ship ship = new Ship(name, type, weight, actualTimeOfArrival, scheduledTimeOfArrival);

            timetable.add(ship);
        }
        Collections.sort(timetable);
    }

    public List<Ship> getTimetable() {
        return timetable;
    }

    public static void main(String[] args) {
        TimetableGenerator generator = new TimetableGenerator(90);
        List<Ship> schedule = generator.getTimetable();
        int i = 0;
        for (Ship ship: schedule)
        {
            System.out.println("\n-------------------------" + ++i +"----------------------------\n" +
                    ship + "\n-------------------------------------------------------\n");
        }
        System.out.println(schedule.size());
        System.out.println(new GregorianCalendar().getTime());
        JsonWriter writer = new JsonWriter();
        writer.writeToJson(schedule, "src\\\\resources\\\\ships.json");
    }
}
