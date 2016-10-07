package main;

import java.util.concurrent.ConcurrentHashMap;

public class ForgettingMap<K, V> extends ConcurrentHashMap<K, V>{

	private int limit;
	
	public ForgettingMap(int limit) {
	//	super(limit);			// might be a waste to allocate too much memory for large limits
		this.limit = limit;
	}

	public void add(K key, V value) {
		put(key, value);
	}
	
	public V find(K key) {
		return get(key);
	}
	
}
