package main;

public class ForgettingMap<K, V> {

	private int limit;
	private Entry<K,V>[] entries;
	Entry<K,V> headOfChain;
	private int size = 0;
	
	public ForgettingMap(int limit) {
		this.limit = limit;
		entries = new Entry[limit * 16];
	}

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
			if(headOfChain == null) {
				headOfChain = newEntry;
			}else{
				headOfChain.prev = newEntry;
				newEntry.prev = null;
				newEntry.next = headOfChain;
				headOfChain = newEntry;
			}
			size ++;
//			System.out.println("added k:" + key + " v:" + value + " hash:" + hash + " to slot: " + i);
		}
	}

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
				
				e0.next = e2;
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
		
//		System.out.println("removing item " + headOfChain.key);
		
		// remove from array
		remove(headOfChain.key);
		
		// remove from chain
		headOfChain = headOfChain.next;
		headOfChain.prev = null;
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
	
	static int hash(Object key) {
		return key.hashCode() * 31;
	}
	
	static int indexFor(int h, int length) {
		return h & (length-1);
	}
}
