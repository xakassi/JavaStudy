package netcracker.practice.collections;

import java.util.LinkedList;

public class Order {
    private Integer numberOfTable;
    private LinkedList<String> dishes;

    public Order(Integer numberOfTable, LinkedList<String> dishes) {
        this.numberOfTable = numberOfTable;
        this.dishes = dishes;
    }

    public Integer getNumberOfTable() {
        return numberOfTable;
    }

    public LinkedList<String> getDishes() {
        return dishes;
    }
}
