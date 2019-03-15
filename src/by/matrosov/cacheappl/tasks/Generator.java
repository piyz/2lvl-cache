package by.matrosov.cacheappl.tasks;

import by.matrosov.cacheappl.MyCustomCache;

import java.util.Optional;
import java.util.Random;

public class Generator implements Runnable{

    private MyCustomCache<Integer, String> myCustomCache;

    public Generator(MyCustomCache<Integer, String> myCustomCache) {
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
                    myCustomCache.get(key);
                }else if (options.get() == Options.ADD){
                    myCustomCache.add(key, value);
                }else if (options.get() == Options.REMOVE){
                    //myCustomCache.remove(key);
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
