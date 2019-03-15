package by.matrosov.cacheappl;

public interface Cache<K,V> {
    void add(K key, V value);
    void remove(K key);
    void get(K key);
    void printFirstLayer();
    void printSecondLayer();
}
