package domein;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class VerzoekLogger {
    //TODO attrib
    private BlockingQueue<String> logQueue = new ArrayBlockingQueue<>(1000);

    public  void log(String string) {
      //TODO
        try {
            logQueue.put(string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public String haalLogOp(){
       //TODO

        try {
            return logQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
