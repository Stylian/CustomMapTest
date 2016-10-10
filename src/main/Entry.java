package main;

public class Entry<K,V> {
	final K key;
	V value;
	volatile Entry<K,V> prev;
	volatile Entry<K,V> next;
	int hash;
	volatile int searched = 0;
	
	public Entry(K key, V value, int hash) {
		this.key = key;
		this.value = value;
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "Entry [key=" + key + ", value=" + value + ", searched=" + searched + "]";
	}

}
