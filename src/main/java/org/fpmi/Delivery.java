package org.fpmi;

public class Delivery {
    private int distance;
    private Size size;
    private Boolean fragile;
    private Load load;

    public Delivery(Integer distance, Size size, Boolean fragile, Load load){
        this.distance = distance;
        this.size = size;
        this.fragile = fragile;
        this.load = load;
    }

    public static Double getCost(Delivery order) {
       return (getCostDistance(order.distance) +  getCostFragile(order.fragile, order.distance)) * getCostLoad(order.load);
    }
    public static Double getCostDistance(Integer distance){
        if (distance >= 30) {
            return 300.0;
        } else if (distance < 30 && distance >= 10) {
            return 200.0;
        } else if (distance < 10 && distance>=2) {
            return 100.0;
        } else {
            return 50.0;
        }
    }

    public Double getCostSize(Size size){
        if (size.equals(Size.BIG)) {
            return 200.0;
        } else
            return 100.0;
    }

    public static Double getCostFragile(boolean fragile, Integer distance){
        if (fragile && distance > 30)  {
            throw new RuntimeException("Невозможно доставить груз");
        } else if (fragile && distance < 30) {
            return 300.0;
        } else {
            return 0.0;
        }
    }

    public static Double getCostLoad(Load load){
        switch (load){
            case EXTREMELY_HIGH -> {
                return 1.6;
            }
            case VERY_HIGH -> {
                return 1.4;
            }
            case HIGH -> {
                return 1.2;
            }
            default -> {
                return 1.0;
            }
        }

    }
}


