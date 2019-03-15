package by.matrosov.cacheappl.layers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondLayer<K extends Serializable, V extends Serializable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondLayer.class);

    private static final String FILE_NAME = "data.dat";
    private static boolean emptyFlag = true;

    SecondLayer() {
    }

    void add(Map.Entry<K, V> entry){
        Map<K,V> map;
        if (emptyFlag){
            map = new HashMap<>();
            emptyFlag = false;
        }else {
            map = getAll();
        }
        map.put(entry.getKey(), entry.getValue());
        write2file(map);
    }

    public V get(K key){

        Map<K,V> map;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){

            map = (Map<K, V>) ois.readObject();
            for (K k : map.keySet()){
                if (k.hashCode() == key.hashCode()){ //hm? how to compare with type K
                    return map.get(key);
                }
            }
            return null;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void remove(K key){
        Map<K,V> map = getAll();
        for (K k : map.keySet()){
            if (k.hashCode() == key.hashCode()){
                map.remove(k);
                LOGGER.info("successful remove from second layer");
                write2file(map);
                return;
            }
        }
        LOGGER.info("Oops... Object with key = " + key + " not found on second layer");
    }

    public void print(){
        Map<K,V> map;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            List<String> list = new ArrayList<>();
            map = (Map<K, V>) ois.readObject();
            map.forEach((k,v)-> list.add(k + "-" + v));
            LOGGER.info("Second layer: " + list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<K,V> getAll(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (Map<K, V>) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void write2file(Map<K,V> map){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
