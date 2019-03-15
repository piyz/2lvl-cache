package by.matrosov.cacheappl;

import by.matrosov.cacheappl.layers.FirstLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            LOGGER.info("remove object with key = " + key + " from first layer");
            firstLayer.remove(key);
        }else {
            LOGGER.info("remove object with key = " + key + " from second layer");
            firstLayer.getSecondLayer().remove(key);
        }
    }

    @Override
    public void get(K key) {
        Object object;
        if (firstLayer.containsKey(key)){
            LOGGER.info("getting object from first layer... ");
            object = firstLayer.get(key);
        }else {
            LOGGER.info("getting object from second layer...");
            object = firstLayer.getSecondLayer().get(key);
        }

        if (object == null){
            LOGGER.info("Oops...object with key " + key + " not found");
        }else {
            LOGGER.info("Found object: " + object);
        }
    }

    @Override
    public void printFirstLayer() {
        List<String> list = new ArrayList<>();
        firstLayer.forEach((k,v)-> list.add(k + " - " + v));
        LOGGER.info("First layer: " + list);
    }

    @Override
    public void printSecondLayer() {
        firstLayer.getSecondLayer().print();
    }

    @Override
    public void printAllLayers() {
        printFirstLayer();
        printSecondLayer();
    }
}
