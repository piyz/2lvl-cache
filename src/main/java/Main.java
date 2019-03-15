import by.matrosov.cacheappl.MyCustomCache;
import by.matrosov.cacheappl.layers.FirstLayer;
import by.matrosov.cacheappl.tasks.ObjectGenerator;
import by.matrosov.cacheappl.tasks.PulseGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        FirstLayer<Integer, String> firstLayer = new FirstLayer<>();
        MyCustomCache<Integer, String> myCustomCache = new MyCustomCache<>(firstLayer);

        ObjectGenerator objectGenerator = new ObjectGenerator(myCustomCache);
        PulseGenerator pulseGenerator = new PulseGenerator(myCustomCache);
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.execute(objectGenerator);
        service.execute(pulseGenerator);
    }
}
