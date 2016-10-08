package main;

import java.util.concurrent.ConcurrentHashMap;

public class ForgettingMap<K, V> {

	private int limit;
	private ConcurrentHashMap<K, V> entries;
	private ConcurrentHashMap<K, Integer> searches;
	
	public ForgettingMap(int limit) {
		this.limit = limit;
		entries = new ConcurrentHashMap<>(limit * 4);
		searches = new ConcurrentHashMap<>(limit * 4);
	}

	public void add(K key, V value) {
		if(entries.mappingCount() == limit) {
			removeLeastSearched();
		}
		
		entries.put(key, value);
		
		searches.put(key, 1);
	}
	
	public V find(K key) {
		searches.put(key, searches.get(key) + 1);
		
		return entries.get(key);
	}
	
	private void removeLeastSearched() {
		
//		entries.remove(key);
	}
	
}
