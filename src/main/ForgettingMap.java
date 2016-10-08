package main;

public class ForgettingMap<K, V> {

	private int limit;
	private Entry<K,V>[] entries;
	
	public ForgettingMap(int limit) {
		this.limit = limit;
		entries = new Entry[limit * 4];
	}

	public void add(K key, V value) {
		
		synchronized (this) {
			
			if(key == null) {
				return;
			}
			
			int hash = hash(key.hashCode());
			int i = indexFor(hash, entries.length);
			
			//replace if exists
			for(Entry<K,V> e = entries[i]; e != null; e = e.next) {
				Object k;
				if(e.hash == hash && ((k=e.key) == key || key.equals(k))) {
					e.value = value;
					System.out.println("replaced k:" + key + " v:" + value + " hash:" + hash + " to index: " + i);
					return;
				}
			}
			
			// add new
			entries[i] = new Entry<K, V>(key, value, entries[i], hash);
			System.out.println("added k:" + key + " v:" + value + " hash:" + hash + " to index: " + i);
		}
	}
	
	public V find(Object key) {
		if(key == null) {
			return null;
		}
		
		int hash = hash(key.hashCode());
		
		for(Entry<K,V> e = entries[indexFor(hash, entries.length)];
			e != null;
			e = e.next) {
			
			Object k;
			if(e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				return e.value;
			}
		}
		
		return null;
	}
	
	private void removeLeastSearched() {
		
//		entries.remove(key);
	}
	
	
	static int hash(Object key) {
		return key.hashCode() * 31;
	}
	
	static int indexFor(int h, int length) {
		return h & (length-1);
	}
}
