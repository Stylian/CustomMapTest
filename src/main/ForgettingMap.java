package main;

import java.util.Iterator;

/**
 * creates a hash map (without buckets) of a specific item limit and 
 * removes items with the least searches once the limit is reached.
 * <br/>
 * How it works
 * <br/>
 * Each entry hold a reference to the above and below entries in a stack. Once an item is searched 
 * it increments its search value and pushes itself lower to the stack using bubble sort. If the map is full 
 * then the top item of the stack is popped out and replaced on addition.
 */
public class ForgettingMap<K, V> implements Iterable<Entry<K,V>> {

	private int limit;
	private Entry<K,V>[] entries;
	private volatile Entry<K,V> headOfStack;
	private volatile int size = 0;
	
	/**
	 * 
	 * @param limit the maximum number of items that the map should hold
	 */
	public ForgettingMap(int limit) {
		this.limit = limit;
		entries = new Entry[limit * 16];
	}

	/**
	 * Adds a new item to the map, removes the least searched if the map is full
	 */
	public void add(K key, V value) {
		
		synchronized(this) {
			if(key == null) {
				return;
			}
			
			int hash = hash(key.hashCode());
			int i = indexFor(hash, entries.length);
			
			Entry<K,V> e = entries[i];
			if(e != null) {
				Object k;
				if(e.hash == hash && ((k=e.key) == key || key.equals(k))) {
					e.value = value;
//					System.out.println("replaced k:" + key + " v:" + value + " hash:" + hash + " to slot: " + i);
					return;
				}
			}
			
			// add new
			if(size >= limit) {
				removeLeastUsed();
			}
			
			Entry<K,V> newEntry = new Entry<K, V>(key, value, hash);
			entries[i] = newEntry;
			
			//put new entry to head of chain
			if(headOfStack == null) {
				headOfStack = newEntry;
			}else{
				headOfStack.prev = newEntry;
				newEntry.prev = null;
				newEntry.next = headOfStack;
				headOfStack = newEntry;
			}
			size ++;
//			System.out.println("added k: " + key + " v: " + value + " to slot: " + i);
		}
	}

	/**
	 * Searches for an entry and retrieves its value
	 * @param key the key of the entry requested
	 * @return the value of the entry
	 */
	public V find(Object key) {
		synchronized(this) {
			if(key == null) {
				return null;
			}
			
			int hash = hash(key.hashCode());
			
			Entry<K,V> e = entries[indexFor(hash, entries.length)];
			if(e != null) {
				Object k;
				if(e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//					System.out.println("found k:"+key + " v:" + e.value);
					e.searched ++;
					sort(e);
					return e.value;
				}
			}
		}
		
		return null;
	}

	private void sort(Entry<K, V> e) {
		while(e.next != null) {
			if(e.searched > e.next.searched) {
				//push down
				Entry<K,V> e0 = e.prev;
				Entry<K,V> e1 = e;
				Entry<K,V> e2 = e.next;
				Entry<K,V> e3 = e.next.next;
				
				if(e0 != null) {
					e0.next = e2;
				}else{
					headOfStack = e2;
				}
				e1.prev = e2;
				e1.next = e3;
				e2.prev = e0;
				e2.next = e1;
				if(e3 != null) {
					e3.prev = e1;
				}

			}else {
				break;
			}
		}
	}

	private void removeLeastUsed() {
		
//		System.out.println("removing entry: " + headOfStack.key);
		
		// remove from array
		remove(headOfStack.key);
		
		// remove from chain
		headOfStack = headOfStack.next;
		headOfStack.prev = null;
	}

	private void remove(Object key) {
		if(key == null) {
			return;
		}
		
		int hash = hash(key.hashCode());
		int i = indexFor(hash, entries.length);

		Entry<K, V> e = entries[i];

		if(e != null) {
			Object k;
			if(e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				entries[i] = null;
				size--;
			}
		}
	}

	public int size() {
		return size;
	}
	
	public Entry<K, V>[] getEntries() {
		return entries;
	}

	public Entry<K, V> getHeadOfStack() {
		return headOfStack;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new Iterator<Entry<K, V>>(){
			Entry<K,V> next = headOfStack;
			
			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public Entry<K, V> next() {
				Entry<K,V> entr = next;
				next = next.next;
				return entr;
			}

			@Override
			public void remove() {
			}};
	}
	
	static int hash(Object key) {
		return key.hashCode() * 31;
	}
	
	static int indexFor(int h, int length) {
		return h & (length-1);
	}

}
