import by.matrosov.cacheappl.MyCustomCache;
import by.matrosov.cacheappl.layers.FirstLayer;
import by.matrosov.cacheappl.tasks.Options;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        FirstLayer<Integer, String> firstLayer = new FirstLayer<>();
        MyCustomCache<Integer, String> myCustomCache = new MyCustomCache<>(firstLayer);

        //Generator generator = new Generator(myCustomCache);
        //ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //service.execute(generator);



        myCustomCache.add(1, "first");
        myCustomCache.add(2, "second");
        myCustomCache.add(3, "third");
        myCustomCache.add(4, "fourth");
        myCustomCache.add(5, "fifth");
        myCustomCache.add(6, "sixth");

        System.out.println("this is first layer");
        myCustomCache.printFirstLayer();

        System.out.println("this is second layer");
        myCustomCache.printSecondLayer();

        System.out.println("get by key");
        myCustomCache.get(3);
        myCustomCache.get(2);

        Optional<Options> options = Options.valueOf(1);
        System.out.println(options.get());

    }
}
