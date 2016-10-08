package main;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(5);
		
		Thread inputWorker1 = new Thread( () -> {
			map.add("New York", 8550405);
			map.add("Chicago", 269598);
			map.add("Philadelphia", 1526006);
			map.add("Houston", 2296224);
			map.add("Los Angeles", 3792621);
			map.add("Phoenix", 1445632);
			map.add("San Antonio", 1327407);
			map.add("San Diego", 1307402);
		});
		
		Thread inputWorker2 = new Thread( () -> {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			map.add("New York", 8550875);
			map.add("Chicago", 299590);
			map.add("Houston", 2296224);
			map.add("Phoenix", 1445632);
			map.add("San Diego", 1307402);
		});
		
		Thread searchWorker1 = new Thread( () -> {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			map.find("Phoenix");
			map.find("Philadelphia");
			map.find("Phoenix");
			map.find("Angeles");
			map.find("Phoenix");
			map.find("San Diego");
		});
		
		Thread searchWorker2 = new Thread( () -> {
			map.find("Philadelphia");
			map.find("Philadelphia");
			map.find("Houston");
			map.find("Houston");
			map.find("Phoenix");
			map.find("San Diego");
		});
		
		inputWorker1.start();
		inputWorker2.start();

		searchWorker1.start();
		searchWorker2.start();
	}

}
