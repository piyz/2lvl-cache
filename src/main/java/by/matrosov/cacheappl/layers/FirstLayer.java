package by.matrosov.cacheappl.layers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstLayer<K extends Serializable,V extends Serializable> extends LinkedHashMap<K,V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstLayer.class);

    private static final int MAX_SIZE = 4;
    private SecondLayer<K,V> secondLayer = new SecondLayer<>();

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {

        //add eldest element to second layer
        if (size() > MAX_SIZE){
            LOGGER.info("adding to second layer");
            secondLayer.add(eldest);
            return true;
        }else {
            LOGGER.info("adding to first layer");
            return false;
        }
    }

    public SecondLayer<K, V> getSecondLayer() {
        return secondLayer;
    }
}
