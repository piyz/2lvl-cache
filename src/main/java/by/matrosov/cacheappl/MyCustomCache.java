package by.matrosov.cacheappl;

import by.matrosov.cacheappl.layers.FirstLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class MyCustomCache <K extends Serializable, V extends Serializable> implements Cache <K,V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomCache.class);

    private FirstLayer<K,V> firstLayer;

    public MyCustomCache(FirstLayer<K, V> firstLayer) {
        this.firstLayer = firstLayer;
    }

    @Override
    public void add(K key, V value) {
        firstLayer.put(key, value);
    }

    @Override
    public void remove(K key) {
        if (firstLayer.containsKey(key)){
            firstLayer.remove(key);
        }else {
            firstLayer.getSecondLayer().remove(key);
        }
    }

    @Override
    public void get(K key) {
        if (firstLayer.containsKey(key)){
            LOGGER.info("getting object from first layer... " + firstLayer.get(key));
        }else {
            LOGGER.info("getting object from second layer... " + firstLayer.getSecondLayer().get(key));
        }
    }

    @Override
    public void printFirstLayer() {
        firstLayer.forEach((k,v)-> System.out.println(k + "," + v));
    }

    @Override
    public void printSecondLayer() {
        firstLayer.getSecondLayer().print();
    }

    @Override
    public void printAllLayers() {
        System.out.println("This is the first layer:");
        printFirstLayer();
        System.out.println("This is the second layer");
        printSecondLayer();
    }
}
