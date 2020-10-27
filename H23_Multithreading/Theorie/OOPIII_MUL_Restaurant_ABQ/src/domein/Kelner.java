package domein;

import java.security.SecureRandom;

public class Kelner implements Runnable {

    private static final SecureRandom random = new SecureRandom();
    private final Restaurant restaurant;
    private final String naam;

    public Kelner(Restaurant restaurant, String naam) {
        this.restaurant = restaurant;
        this.naam = naam;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(random.nextInt(2000));
                Order order = restaurant.haalOrderOp();
                System.out.printf("Kelder %s krijgt %s%n", naam, order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
