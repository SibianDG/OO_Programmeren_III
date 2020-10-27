package domein;
import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

// VUL DE KLASSE VERDER AAN
public class Tafel {

    private static final SecureRandom generator = new SecureRandom();
    private boolean vatLeeg = false;
    private ArrayBlockingQueue<Boolean> emmerBuffer;

    public Tafel(int aantalEmmers) {
        emmerBuffer = new ArrayBlockingQueue<>(aantalEmmers);
    }

    public void vulEmmer() {
        try {
            Thread.sleep(generator.nextInt(1000)+1000);
            emmerBuffer.put(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public boolean pakEmmer() { //take met timeout ==> poll met tijdsargument
        // herbekijk les Prog3_les6_27/10 1:42:49
        Boolean value = null;
        do {
            try {
                value = emmerBuffer.poll(50L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!vatLeeg && value == null);
        return value != null;
    }

    public void setVatIsLeeg() {
        vatLeeg = true;
    }

}
