package main;

public class Entry<K,V> {
	final K key;
	V value;
	
	/**
	 * the entry above in the stack
	 */
	Entry<K,V> prev;
	
	/**
	 * the entry below in the stack
	 */
	Entry<K,V> next;
	
	int hash;
	
	/**
	 * the number of times this item was searched since added
	 */
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
