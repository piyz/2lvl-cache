package by.matrosov.cacheappl.layers;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstLayer<K,V> extends LinkedHashMap<K,V> {

    private final int MAX_SIZE = 8;
    private SecondLayer<K,V> secondLayer;

    //or write smth like loadFactory in map
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //System.out.println(eldest); //why it going into console 3 times?

        //add eldest element to second layer
        secondLayer.add(eldest);

        //remove eldest element from first layer
        return size() > MAX_SIZE;
    }

    public SecondLayer<K, V> getSecondLayer() {
        return secondLayer;
    }
}
