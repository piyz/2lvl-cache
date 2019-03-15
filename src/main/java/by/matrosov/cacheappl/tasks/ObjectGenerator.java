package by.matrosov.cacheappl.tasks;

import by.matrosov.cacheappl.MyCustomCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Random;

public class ObjectGenerator implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectGenerator.class);

    private MyCustomCache<Integer, String> myCustomCache;

    public ObjectGenerator(MyCustomCache<Integer, String> myCustomCache) {
        this.myCustomCache = myCustomCache;
    }

    @Override
    public void run() {
        while (true){
            try {
                int key = new Random().nextInt(20) + 1;
                String value = generateRandomString();

                Optional<Options> options = generateRandomOptions();
                if (options.get() == Options.GET){
                    LOGGER.info("start getting object...");
                    myCustomCache.get(key);
                }else if (options.get() == Options.ADD){
                    LOGGER.info("start adding object with key = " + key);
                    myCustomCache.add(key, value);
                }else if (options.get() == Options.REMOVE){
                    LOGGER.info("start removing object with key = " + key);
                    myCustomCache.remove(key);
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomString(){
        Random random = new Random();
        return Integer.toHexString(random.nextInt());
    }

    private Optional<Options> generateRandomOptions(){
        int random = new Random().nextInt(3);
        return Options.valueOf(random);
    }
}
