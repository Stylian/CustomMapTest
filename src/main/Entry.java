package main;

public class Entry<K,V> {
	K key;
	V value;
	Entry<K,V> next;
	int hash;
	
	public Entry() {
		
	}

	public Entry(K key, V value, Entry<K, V> next, int hash) {
		this.key = key;
		this.value = value;
		this.next = next;
		this.hash = hash;
	}


}
