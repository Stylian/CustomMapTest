package main;

public class ForgettingMap<K, V> {

	private int limit;
	private Entry<K,V>[] entries;
	private int size = 0;
	
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
					System.out.println("replaced k:" + key + " v:" + value + " hash:" + hash + " to bucket: " + i);
					return;
				}
			}
			
			// add new
			if(size >= limit) {
				removeLeastUsed();
			}
			
			entries[i] = new Entry<K, V>(key, value, entries[i], hash);
			size ++;
			System.out.println("added k:" + key + " v:" + value + " hash:" + hash + " to bucket: " + i);
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
				System.out.println("found k:"+key + " v:" + e.value);
				return e.value;
			}
		}
		
		return null;
	}
	
	private void removeLeastUsed() {
		System.out.println("removing item");
		
		//remove("Philadelphia");
	}
	
	public void remove(Object key) {
		int hash = (key == null) ? 0 : hash(key.hashCode());
		int i = indexFor(hash, entries.length);
		Entry<K, V> prev = entries[i];
		Entry<K, V> e = prev;

		while (e != null) {
			Entry<K, V> next = e.next;
			Object k;
			if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
				if (prev == e)
					entries[i] = next;
				else
					prev.next = next;
			}
		}
		size--;
	}
	
	
	static int hash(Object key) {
		return key.hashCode() * 31;
	}
	
	static int indexFor(int h, int length) {
		return h & (length-1);
	}
}
