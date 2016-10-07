package main;

import java.util.concurrent.ConcurrentHashMap;

public class ForgettingMap<K, V> extends ConcurrentHashMap<K, V>{

	public ForgettingMap() {
		
	}

	public void add(K key, V value) {
		put(key, value);
	}
	
	
}
