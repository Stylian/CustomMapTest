package main;

public class Entry<K,V> {
	final K key;
	V value;
	Entry<K,V> prev;
	Entry<K,V> next;
	int hash;
	int searched = 0;
	
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
