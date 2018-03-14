package netcracker.practice.collections;

import java.util.ArrayList;
import java.util.List;

public interface CatRestaurantService {
    /**
     * Checks whether it is possible to take a cat with name {@code catName},
     * is this cat free now
     *
     * @param catName the name of the desired cat
     * @return {@code true} result if the cat is free
     */
    boolean isCatAvailable(String catName);

    /**
     * The method for ordering in a restaurant.
     * Accepts as a parameter the number of the table {@code numbetOfTable}
     * and the list of selected menu items {@code menuItems}.
     * The order is placed in the queue
     *
     * @param numbetOfTable the number of the table that made the order
     * @param menuItems     menu item numbers that have been chosen
     */
    void createOrder(int numbetOfTable, ArrayList<Integer> menuItems);

    /**
     * A method for taking an order from the orders queue.
     * The order is taken by a free waiter from the order of the waiters
     *
     * @return Order that was taken
     */
    Order getUntouchedOrder();

    /**
     * Getter for {@link CatRestaurantServiceImpl#menu}
     *
     * @return {@link CatRestaurantServiceImpl#menu}
     */
    List<String> getMenu();

    /**
     * Method that gets any currently free cat from {@link CatRestaurantServiceImpl#cats}
     *
     * @return Any free cat from {@link CatRestaurantServiceImpl#cats}
     */
    String chooseCat();

    /**
     * Shows the number of orders processed by each waiter
     */
    void getWaitersStatistic();
}
