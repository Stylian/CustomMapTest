package main;

public class ForgettingMap<K, V> {

	private int limit;
	private Entry<K,V>[] entries;
	private int size = 0;
	
	public ForgettingMap(int limit) {
		this.limit = limit;
		entries = new Entry[limit * 16];
	}

	public void add(K key, V value) {
		
		synchronized (this) {
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
					System.out.println("replaced k:" + key + " v:" + value + " hash:" + hash + " to slot: " + i);
					return;
				}
			}
			
			// add new
			if(size >= limit) {
				removeLeastUsed();
			}
			
			entries[i] = new Entry<K, V>(key, value, entries[i], hash);
			size ++;
			System.out.println("added k:" + key + " v:" + value + " hash:" + hash + " to slot: " + i);
		}
	}

	public V find(Object key) {
		if(key == null) {
			return null;
		}
		
		int hash = hash(key.hashCode());
		
		Entry<K,V> e = entries[indexFor(hash, entries.length)];
		if(e != null) {
			Object k;
			if(e.hash == hash && ((k = e.key) == key || key.equals(k))) {
				System.out.println("found k:"+key + " v:" + e.value);
				e.searched ++;
				return e.value;
			}
		}
		
		return null;
	}
	
	
	private K getLeastSearched() {
			
		//test
		return (K) "Chicago";
	}

	
	private void removeLeastUsed() {
		
		K kls = getLeastSearched();
		System.out.println("removing item " + kls);
		
		remove(kls);
	}

	public void remove(Object key) {
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
