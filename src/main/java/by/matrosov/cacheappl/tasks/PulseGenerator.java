package by.matrosov.cacheappl.tasks;

import by.matrosov.cacheappl.MyCustomCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PulseGenerator implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(PulseGenerator.class);

    private MyCustomCache<Integer, String> myCustomCache;

    public PulseGenerator(MyCustomCache<Integer, String> myCustomCache) {
        this.myCustomCache = myCustomCache;
    }

    @Override
    public void run() {
        while (true){
            try {
                LOGGER.info("service going well");
                Thread.sleep(10000);
                //out all layers?
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
