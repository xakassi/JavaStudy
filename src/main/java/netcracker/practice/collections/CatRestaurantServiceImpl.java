package netcracker.practice.collections;

import java.util.*;

public class CatRestaurantServiceImpl implements CatRestaurantService {
    private static CatRestaurantServiceImpl instance;
    private HashSet<String> cats;
    private HashSet<String> waiters;
    private HashSet<String> busyCats;
    private List<String> menu;
    private Queue<Order> ordersQueue;
    private Queue<String> freeWaitersQueue;
    private HashSet<String> busyWaiters;
    private Map<String, Integer> countOfOrdersForWaiters;

    private DataStoringJob dataStoringJob = new DataStoringJob();

    private CatRestaurantServiceImpl() {
        cats = getCats();
        waiters = getWaiters();

        busyCats = new HashSet<>();
        ordersQueue = new LinkedList<>();

        freeWaitersQueue = new LinkedList<>();
        freeWaitersQueue.addAll(waiters);
        busyWaiters = new HashSet<>();

        countOfOrdersForWaiters = new HashMap<>();

        Thread releaseWaitersAndCatsThread = new Thread(() -> {
            while (true) {
                if (!busyWaiters.isEmpty()) {
                    String waiter = busyWaiters.stream().findAny().get();
                    if (waiter != null) {
                        freeWaitersQueue.add(waiter);
                    }
                }

                if (!busyCats.isEmpty()) {
                    String cat = busyCats.stream().findAny().get();
                    if (cat != null) {
                        busyCats.remove(cat);
                    }
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        releaseWaitersAndCatsThread.setDaemon(true);
        releaseWaitersAndCatsThread.start();
    }

    public static CatRestaurantServiceImpl getInstance() {
        if (instance == null) {
            instance = new CatRestaurantServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean isCatAvailable(String catName) {
        return !busyCats.contains(catName);
    }

    @Override
    public void createOrder(int numberOfTable, ArrayList<Integer> menuItems) {
        LinkedList<String> dishes = new LinkedList<>();
        for (int item : menuItems) dishes.add(menu.get(item));
        ordersQueue.add(new Order(numberOfTable, dishes));
        System.out.println("Ваш заказ: " + dishes);
        getUntouchedOrder();
    }

    @Override
    public String chooseCat() {
        String catName = "";
        try {
            catName = (cats.stream().filter(cat -> !busyCats.contains(cat))).findAny().get();
            if (catName != null) {
                busyCats.add(catName);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все котики сейчас заняты. Попробуйте позже.");
        }
        return catName;
    }

    @Override
    public Order getUntouchedOrder() {
        Order order = ordersQueue.poll();
        if (order != null) {
            String waiterName = freeWaitersQueue.poll();

            if (waiterName == null)
                System.out.println("К сожалению все официанты заняты, пожалуйста, подождите немного.");
            while (waiterName == null) {
                waiterName = freeWaitersQueue.poll();
            }

            System.out.println("Вашим официантом будет " + waiterName);
            busyWaiters.add(waiterName);
            Integer countOfOrdersForThisWaiter = countOfOrdersForWaiters.get(waiterName);
            if (countOfOrdersForThisWaiter != null) {
                countOfOrdersForWaiters.put(waiterName, countOfOrdersForThisWaiter + 1);
            } else {
                countOfOrdersForWaiters.put(waiterName, 1);
            }
        }
        return order;
    }

    @Override
    public List<String> getMenu() {
        if (menu == null) {
            menu = dataStoringJob.readMenuFromFile();
            if (menu == null) {
                menu = new ArrayList<>();
            }
        }
        return menu;
    }

    public HashSet<String> getCats() {
        if (cats == null) {
            cats = dataStoringJob.readCatsFromFile();
            if (cats == null) {
                cats = new HashSet<>();
            }
        }
        return cats;
    }

    public HashSet<String> getWaiters() {
        if (waiters == null) {
            waiters = dataStoringJob.readWaitersFromFile();
            if (waiters == null) {
                waiters = new HashSet<>();
            }
        }
        return waiters;
    }

    public void getWaitersStatistic() {
        System.out.println(countOfOrdersForWaiters);
    }

}
