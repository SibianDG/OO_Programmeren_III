package domein;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VerzoekLogger2 {
    //ALS OEFENING, maar VerzoekLogger is beter
    //TODO attrib
    //private BlockingQueue<String> logQueue = new ArrayBlockingQueue<>(1000);
    private String[] logArray = new String[1000];
    private int writeIndex = 0;
    private int readIndex = 0;
    private int total = 0;

    private Lock accessLock = new ReentrantLock();
    private Condition canWrite = accessLock.newCondition();
    private Condition canRead = accessLock.newCondition();

    public  void log(String string) {
      //TODO
        accessLock.lock();
        try {
            while (total == logArray.length) {
                canWrite.await();
            }
            logArray[writeIndex] = string;
            writeIndex = (writeIndex+1) % logArray.length;
            total++;
            canRead.signal();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            accessLock.unlock();
        }
    }

    public String haalLogOp(){
        String readValue = "";
        accessLock.lock();
        try {
            while (total == 0) {
                canRead.await();
            }
            readValue = logArray[readIndex];
            readIndex = (readIndex+1) % logArray.length;
            total--;
            canWrite.signal();
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            accessLock.unlock();
        }
        return readValue;
    }
    
}
