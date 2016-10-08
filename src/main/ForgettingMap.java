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
		
		synchronized (this) {
			if(entries.get(key) == null) {
				if(entries.mappingCount() == limit) {
					removeLeastSearched();
				}
				searches.put(key, 0);
			}
			entries.put(key, value);
		}
	}
	
	public V find(K key) {
		searches.put(key, searches.get(key) + 1);
		
		return entries.get(key);
	}
	
	private void removeLeastSearched() {
		
//		entries.remove(key);
	}
	
}
