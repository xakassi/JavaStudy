package netcracker.practice.collections;

import java.util.ArrayList;
import java.util.List;

public interface CatRestaurantService {
    /**
     * Метод для проверки, можно ли выбрать себе котика
     * с именем catName, свободен ли он сейчас
     */
    boolean isCatAvailable(String catName);

    /**
     * Метод для оформления заказа - указывается номер столика numbetOfTable
     * и перечень выбранных пунктов меню menuItems,
     * заказ помещается в очередь
     */
    void createOrder(int numbetOfTable, ArrayList<Integer> menuItems);

    /**
     * Метод для взятия заказа из очереди,
     * заказ берёт свободный официант из очередт официантов
     */
    Order getUntouchedOrder();

    /**
     * Getter для menu
     */
    List<String> getMenu();

    /**
     * Метод, возвращающий любого свободного на данный момент котика
     */
    String chooseCat();
}
