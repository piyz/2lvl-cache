package by.matrosov.cacheappl.layers;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstLayer<K extends Serializable,V extends Serializable> extends LinkedHashMap<K,V> {

    private static final int MAX_SIZE = 4;
    private SecondLayer<K,V> secondLayer = new SecondLayer<>();

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {

        //add eldest element to second layer
        if (size() > MAX_SIZE){
            secondLayer.add(eldest);
            return true;
        }else {
            return false;
        }
    }

    public SecondLayer<K, V> getSecondLayer() {
        return secondLayer;
    }
}
