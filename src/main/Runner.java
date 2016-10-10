package main;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		System.out.println("start test");
		
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(4);
//		
//		new Thread( () -> {
//			
//			map.add("Philadelphia", 1526006);
//			map.add("Houston", 2296224);
//			map.add("San Antonio", 1327407);
//			sleep(50);
//			
//			map.add("Los Angeles", 3792621);
//			map.add("Phoenix", 1445632);
//			map.add("San Diego", 1307402);
//			sleep(50);
//			
//			map.add("Houston", 2296224);
//		}).start();
//		
//		new Thread( () -> {
//			
//			sleep(20);
//			map.find("San Antonio");
//			map.find("Philadelphia");
//			map.find("Philadelphia");
//			sleep(20);
//			
//			map.find("San Antonio");
//			map.find("Houston");
//			map.find("Phoenix");
//			
//		}).start();
//		
//		new Thread( () -> {
//			
//			sleep(30);
//			map.add("New York", 8550875);
//			map.add("Chicago", 299590);
//			map.add("San Antonio", 1327407);
//			sleep(50);
//			
//			map.add("Los Angeles", 3798748);
//			map.add("Phoenix", 1445987);
//			map.add("San Diego", 1309001);
//			sleep(50);
//			
//			map.add("Houston", 2296224);
//		}).start();		
//		
//		new Thread( () -> {
//			
//			sleep(20);
//			map.find("Houston");
//			map.find("Los Angeles");
//			map.find("Los Angeles");
//			sleep(20);
//			
//			map.find("San Antonio");
//			map.find("Houston");
//			map.find("Phoenix");
//			
//		}).start();		
//		
//		
//		sleep(200);
//		for(Entry<String, Integer> entry : map) {
//			System.out.println(entry);
//		}
		

		
		Thread t = new Thread(){
		     public void run()
		     {

					map.add("Philadelphia", 1526006);
					map.add("Houston", 2296224);
					map.add("San Antonio", 1327407);
					sleep1(50);
					
					map.add("Los Angeles", 3792621);
					map.add("Phoenix", 1445632);
					map.add("San Diego", 1307402);
					sleep1(50);
					showChain(map);
		     }
		};
		t.start();
		
		
		Thread t1 = new Thread(){
		     public void run()
		     {

		    	 	sleep1(20);
					map.find("Philadelphia");
					sleep1(20);
					
					map.find("San Antonio");
					map.find("Houston");
					map.find("Phoenix");
					showChain(map);
		     }
		};
		t1.start();		
		
		Thread t2 = new Thread(){
		     public void run()
		     {

		    	 sleep1(30);
					map.add("New York", 8550875);
					map.add("Chicago", 299590);
					map.add("San Antonio", 1327407);
					sleep1(50);
					
					map.add("Los Angeles", 3798748);
					map.add("Phoenix", 1445987);
					map.add("San Diego", 1309001);
					sleep1(50);
					
					map.add("Houston", 2296224);
					showChain(map);
		     }
		};
		t2.start();		
		
		Thread t3 = new Thread(){
		     public void run()
		     {

		    	 	sleep1(20);
					map.find("Houston");
					map.find("Los Angeles");
					map.find("Los Angeles");
					sleep1(20);
					
					map.find("San Antonio");
					map.find("Houston");
					map.find("Phoenix");
					sleep1(400);
					showChain(map);
					
					System.out.println("--------------map values-----------------------");
					for(Entry<String, Integer> entry : map) {
						System.out.println(entry);
					}
					
					System.out.println("------------------entries array-----------------");
					int count = 0;
					for(Entry<String, Integer> entry : map.entries) {
						System.out.println("[" + count++ + "] " + entry);
					}
		     }
		};
		t3.start();		

	}
	
	public static void sleep1(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static <K, V> void showChain(ForgettingMap<String, Integer> map) {
		System.out.println("-----------stack--------------");
		for(Entry<String, Integer> e = map.headOfChain; e != null; e = e.next) {
			System.out.println(e.key);
		}
		System.out.println("-------------------------");
	}
}
