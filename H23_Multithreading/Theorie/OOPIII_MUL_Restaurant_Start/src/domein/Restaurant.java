package domein;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private final Lock accessLock = new ReentrantLock();
    private final Condition kanOrderPlaatsen = accessLock.newCondition();
    private final Condition kanOrderWegbrengen = accessLock.newCondition();

    private Order order;  //null => er is geen order, geen bool nodig

    public void plaatsOrder(Order order){
        accessLock.lock();

        try {
            while (this.order != null) {
                kanOrderPlaatsen.await();  // de sleutel wordt automatisch afgegeven
            }                              // krijgt sleutel automatisch terug als beschikbaar
            this.order = order;
            kanOrderWegbrengen.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            accessLock.unlock();
        }
    }

    public Order haalOrderOp(){
        accessLock.lock();
        Order hulp = null;
        try {
            while (order == null) {
                kanOrderWegbrengen.await();  // de sleutel wordt automatisch afgegeven
            }                              // krijgt sleutel automatisch terug als beschikbaar
            hulp = order;
            order = null;
            kanOrderPlaatsen.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            accessLock.unlock();
        }
        return hulp;
    }
}
