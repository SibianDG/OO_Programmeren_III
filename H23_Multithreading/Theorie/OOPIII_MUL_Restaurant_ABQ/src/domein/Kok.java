package domein;

import java.security.SecureRandom;

public class Kok implements Runnable {

    private static final SecureRandom random = new SecureRandom();
    private final Restaurant restaurant;

    public Kok(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(random.nextInt(2000));
                restaurant.plaatsOrder(new Order());
            } catch (InterruptedException e) {
                e.printStackTrace();
                // mooie uitwerking
                Thread.currentThread().interrupt();
            }
        }
    }
}
