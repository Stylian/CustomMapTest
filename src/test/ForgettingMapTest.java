package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.ForgettingMap;

public class ForgettingMapTest {

	@Test
	public void testSingleThread() {
		
		int limit = 5;
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(limit);
		map.add("Philadelphia", 1526006);
		map.add("Houston", 2296224);
		map.add("San Antonio", 1327407);
		map.add("Los Angeles", 3792621);
		map.add("Phoenix", 1445632);
		map.add("San Diego", 1307402);
		
		// should have been removed
		assertEquals(null, map.find("Phoenix"));
		
		map.find("San Antonio");
		map.find("Philadelphia");
		map.find("Philadelphia");
		map.find("San Diego");
		
		map.add("New York", 8550875);
		
		// should have been removed
		assertEquals(null, map.find("Los Angeles"));

		map.add("San Antonio", 1327407);
		map.add("Chicago", 299590);
		
		// check if all items exist as expected
		assertEquals(299590, map.find("Chicago").intValue());
		assertEquals(1327407, map.find("San Antonio").intValue());
		assertEquals(1526006, map.find("Philadelphia").intValue());
		assertEquals(1307402, map.find("San Diego").intValue());
		assertEquals(2296224, map.find("Houston").intValue());
		
		// check if size was respected
		assertEquals(limit, map.size());
	}
	
	@Test
	public void testMultipleThreads() {

		int limit = 4;
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(limit);

		Thread t = new Thread() {
			public void run() {
				map.add("Philadelphia", 1526006);
				map.add("Houston", 2296224);
				map.add("San Antonio", 1327407);
				map.add("Los Angeles", 3792621);
				map.add("Phoenix", 1445632);
				map.add("San Diego", 1307402);
			}
		};
		t.start();

		Thread t1 = new Thread() {
			public void run() {
				map.find("San Antonio");
				map.find("Philadelphia");
				map.find("Philadelphia");
				map.find("San Antonio");
				map.find("Houston");
				map.find("Phoenix");
			}
		};
		t1.start();	

		Thread t2 = new Thread() {
			public void run() {
				map.add("New York", 8550875);
				map.add("Chicago", 299590);
				map.add("San Antonio", 1327407);
				map.add("Phoenix", 1445987);
				map.add("San Diego", 1309001);
				map.add("Houston", 2296224);
			}
		};
		t2.start();

		Thread t3 = new Thread() {
			public void run() {
				map.find("Houston");
				map.find("Los Angeles");
				map.find("Los Angeles");
				map.find("Houston");
				map.find("Phoenix");
			}
		};
		t3.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(limit, map.size());
		
	}
}
