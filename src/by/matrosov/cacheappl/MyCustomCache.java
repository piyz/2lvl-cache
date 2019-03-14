package by.matrosov.cacheappl;

import by.matrosov.cacheappl.layers.FirstLayer;

import java.io.Serializable;

public class MyCustomCache <K extends Serializable, V extends Serializable> implements Cache <K,V> {

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
    public V get(K key) {
        if (firstLayer.containsKey(key)){
            return firstLayer.get(key);
        }else {
            firstLayer.getSecondLayer().get(key);
        }
        return null;
    }
}
