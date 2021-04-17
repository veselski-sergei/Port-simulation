package service3;

import service1.Cargo;

public class HandlerCrane {
    private Cargo type;
    private double productivity;
    private int amount;

    public HandlerCrane(Cargo type) {
        this.type = type;
        amount = 1;
        productivity = getProductivityOfCrane();
    }

    public double getProductivityOfCrane() {
        return switch (type) {
            case BULK -> 2;
            case CONTAINER -> 1.2;
            case LIQUID -> 2.5;
        };
    }

    public Cargo getType() {
        return type;
    }

    public double getProductivity() {
        return productivity;
    }

    public int getAmount() { return amount; }
}
