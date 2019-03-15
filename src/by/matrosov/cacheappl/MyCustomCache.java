package by.matrosov.cacheappl;

import by.matrosov.cacheappl.layers.FirstLayer;

import java.io.Serializable;

public class MyCustomCache <K extends Serializable, V extends Serializable> implements Cache <K,V> {

    //какие элеенты попали
    //каждые 10 сек инфа
    //какие удалены

    //классификацию стратегий, например 4

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
            System.out.println(firstLayer.get(key));
        }else {
            System.out.println(firstLayer.getSecondLayer().get(key));
        }
    }

    @Override
    public void printFirstLayer() {
        firstLayer.forEach((k,v)-> System.out.println("Key : " + k + ", Value : " + v));
    }

    @Override
    public void printSecondLayer() {
        firstLayer.getSecondLayer().print();
    }
}
