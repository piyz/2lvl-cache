package by.matrosov.cacheappl.layers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class SecondLayer<K, V> {
    private static final String FILE_NAME = "data.dat";

    public SecondLayer() {
    }

    void add(Map.Entry<K, V> entry){
        Map<K,V> map = getAll();
        map.put(entry.getKey(), entry.getValue());
        write2file(map);
    }

    public V get(K key){

        Map<K,V> map;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){

            map = (Map<K, V>) ois.readObject();
            for (K k : map.keySet()){
                if (k == key){ //hm? how to compare with type K
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
            if (k == key){
                map.remove(key);
                write2file(map);
                return;
            }
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
