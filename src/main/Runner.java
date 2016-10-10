package main;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		System.out.println("start test");
		
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(4);
		
		new Thread( () -> {
			
			map.add("Philadelphia", 1526006);
			map.add("Houston", 2296224);
			map.add("San Antonio", 1327407);
			sleep(50);
			
			map.add("Los Angeles", 3792621);
			map.add("Phoenix", 1445632);
			map.add("San Diego", 1307402);
			sleep(50);
			
			map.add("Houston", 2296224);
		}).start();
		
		new Thread( () -> {
			
			sleep(20);
			map.find("San Antonio");
			map.find("Philadelphia");
			map.find("Philadelphia");
			sleep(20);
			
			map.find("San Antonio");
			map.find("Houston");
			map.find("Phoenix");
			
		}).start();
		
		new Thread( () -> {
			
			sleep(30);
			map.add("New York", 8550875);
			map.add("Chicago", 299590);
			map.add("San Antonio", 1327407);
			sleep(50);
			
			map.add("Los Angeles", 3798748);
			map.add("Phoenix", 1445987);
			map.add("San Diego", 1309001);
			sleep(50);
			
			map.add("Houston", 2296224);
		}).start();		
		
		new Thread( () -> {
			
			sleep(20);
			map.find("Houston");
			map.find("Los Angeles");
			map.find("Los Angeles");
			sleep(20);
			
			map.find("San Antonio");
			map.find("Houston");
			map.find("Phoenix");
			
		}).start();		
		
		
		sleep(200);
		for(Entry<String, Integer> entry : map) {
			System.out.println(entry);
		}
		
//		System.out.println("start");
//		
//		map.add("Philadelphia", 1526006);
//		showChain(map);
//		map.add("Houston", 2296224);
//		showChain(map);
//		map.add("San Antonio", 1327407);
//		showChain(map);
//		showChain(map);
//		map.add("Los Angeles", 3792621);
//		showChain(map);
//		map.add("Phoenix", 1445632);
//		showChain(map);
//		map.add("San Diego", 1307402);
//		showChain(map);
//		
//		map.find("San Antonio");
//		showChain(map);
//		map.find("San Antonio");
//		showChain(map);
//		map.find("Philadelphia");
//		showChain(map);
//		map.find("Phoenix");
//
//		map.add("New York", 8550875);
//		showChain(map);
//		map.add("Chicago", 299590);
//		showChain(map);
//		map.add("Houston", 2296224);
//		
//		map.find("San Antonio");
//		showChain(map);
//		map.find("Philadelphia");
//		showChain(map);
//		map.find("Phoenix");
//
//		map.add("New York", 8550405);
//		showChain(map);
//		map.add("Chicago", 269598);
//		showChain(map);
//		
//		map.find("Los Angeles");
//		showChain(map);
//		map.find("Phoenix");
//		showChain(map);
//		map.find("San Diego");
//
//		map.find("Philadelphia");
//		showChain(map);
//		map.find("Philadelphia");
//		showChain(map);
//		map.find("Los Angeles");
//		showChain(map);
//		map.find("New York");
//		showChain(map);
//		map.find("Phoenix");
//		showChain(map);
//		map.find("San Diego");
//		showChain(map);
//		
//		map.add("New York", 8550875);
//		showChain(map);
//		map.add("Chicago", 299590);
//		showChain(map);
//		map.add("Houston", 2296224);
//		showChain(map);
//		map.add("Phoenix", 1448798);
//		showChain(map);
//		map.add("San Diego", 1309001);
//		showChain(map);
//		
//		System.out.println("end");
	}
	
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static <K, V> void showChain(ForgettingMap<String, Integer> map) {
//		System.out.println("-----------");
//		for(Entry<String, Integer> e = map.headOfChain; e != null; e = e.next) {
//			System.out.println(e.key);
//		}
	}
}
