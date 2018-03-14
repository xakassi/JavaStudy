package netcracker.practice.collections;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    private static CatRestaurantService catRestaurantService = new CatRestaurantServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mode;
        mark:
        while (true) {
            String offer = "Выберите действие:" +
                    "\n\t1 - Просмотреть меню" +
                    "\n\t2 - Сделать заказ" +
                    "\n\t3 - Получить котика" +
                    "\n\t4 - Просмотреть статистику официантов" +
                    "\n\t0 - Выйти";
            System.out.println(offer);
            mode = scanner.nextInt();
            switch (mode) {
                case 1:
                    System.out.println(catRestaurantService.getMenu());
                    break;
                case 2:
                    System.out.println("Введите свой номер столика:");
                    int number = scanner.nextInt();
                    System.out.println("Введите номера пунктов меню (нажмите 0, когда введёте номера всех блюд):");
                    ArrayList<Integer> dishes = new ArrayList();
                    int dishNumber = -1;
                    while (dishNumber != 0) {
                        dishNumber = scanner.nextInt();
                        if (dishNumber > 0 && dishNumber <= catRestaurantService.getMenu().size()) {
                            dishes.add(dishNumber - 1);
                        }
                    }
                    catRestaurantService.createOrder(number, dishes);
                    break;
                case 3:
                    String cat = catRestaurantService.chooseCat();
                    if (cat != null) {
                        System.out.println("Ваш котик: " + cat);
                    }
                    break;
                case 4:
                    catRestaurantService.getWaitersStatistic();
                    break;
                case 0:
                    break mark;
            }
        }
    }
}
