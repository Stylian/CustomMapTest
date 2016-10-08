package main;

public class Entry<K,V> {
	final K key;
	V value;
	Entry<K,V> next;
	int hash;

	public Entry(K key, V value, Entry<K, V> next, int hash) {
		this.key = key;
		this.value = value;
		this.next = next;
		this.hash = hash;
	}


}
