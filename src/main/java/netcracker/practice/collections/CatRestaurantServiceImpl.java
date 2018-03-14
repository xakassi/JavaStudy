package netcracker.practice.collections;

import org.apache.log4j.Logger;

import java.util.*;

public class CatRestaurantServiceImpl implements CatRestaurantService {
    private Set<String> cats;
    private Set<String> waiters;
    private Set<String> busyCats;
    private List<String> menu;
    private Queue<Order> ordersQueue;
    private Queue<String> freeWaitersQueue;
    private Set<String> busyWaiters;
    private Map<String, Integer> countOfOrdersForWaiters;

    private DataStoringJob dataStoringJob = new DataStoringJob();
    public static final Logger LOG = Logger.getLogger(CatRestaurantServiceImpl.class.getName());

    public CatRestaurantServiceImpl(List<String> menu, Set<String> cats, Set<String> waiters) {
        this.menu = menu;
        this.cats = cats;
        this.waiters = waiters;

        busyCats = new HashSet<>();
        ordersQueue = new LinkedList<>();

        freeWaitersQueue = new LinkedList<>();
        freeWaitersQueue.addAll(waiters);
        busyWaiters = new HashSet<>();

        countOfOrdersForWaiters = new HashMap<>();

        releaseWaitersAndCats();
    }

    public CatRestaurantServiceImpl() {
        this(null, new HashSet<>(), new HashSet<>());
        this.cats = dataStoringJob.readCatsFromFile();
        this.waiters = dataStoringJob.readWaitersFromFile();

        freeWaitersQueue.addAll(waiters);
    }

    private void releaseWaitersAndCats() {
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
                    LOG.error("Thread Exception in CatRestaurantServiceImpl class releaseWaitersAndCats() method!\n", e);
                }
            }
        });
        releaseWaitersAndCatsThread.setDaemon(true);
        releaseWaitersAndCatsThread.start();
    }

    @Override
    public boolean isCatAvailable(String catName) {
        return !busyCats.contains(catName);
    }

    @Override
    public void createOrder(int numberOfTable, ArrayList<Integer> menuItems) {
        LinkedList<String> dishes = new LinkedList<>();
        for (int item : menuItems) {
            dishes.add(menu.get(item));
        }
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
            LOG.info("All cats are busy. Can not choose a cat in CatRestaurantServiceImpl class chooseCat() method!");
        }
        return catName;
    }

    @Override
    public Order getUntouchedOrder() {
        Order order = ordersQueue.poll();
        if (order != null) {
            String waiterName = freeWaitersQueue.poll();

            if (waiterName == null) {
                System.out.println("К сожалению все официанты заняты, пожалуйста, подождите немного.");
                LOG.info("All waiters are busy. Can not get an order in CatRestaurantServiceImpl class getUntouchedOrder() method!");
            }
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

    @Override
    public void getWaitersStatistic() {
        System.out.println(countOfOrdersForWaiters);
    }

}
