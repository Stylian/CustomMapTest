package main;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		// key : city , value : population
		final ForgettingMap<String, Integer> map = new ForgettingMap<>(4);
		

		map.add("New York", 8550405);
		map.add("Chicago", 269598);
		map.add("Philadelphia", 1526006);
		map.add("Houston", 2296224);
		map.add("Los Angeles", 3792621);
		map.add("Phoenix", 1445632);
		map.add("San Antonio", 1327407);
		map.add("San Diego", 1307402);

		map.find("San Antonio");
		map.find("San Antonio");
		map.find("Philadelphia");
		map.find("Phoenix");

		map.add("New York", 8550875);
		map.add("Chicago", 299590);
		map.add("Houston", 2296224);
		
		map.find("San Antonio");
		map.find("Philadelphia");
		map.find("Phoenix");
		map.find("Los Angeles");
		map.find("Phoenix");
		map.find("San Diego");

		map.find("Philadelphia");
		map.find("Philadelphia");
		map.find("Los Angeles");
		map.find("New York");
		map.find("Phoenix");
		map.find("San Diego");

		
		map.add("New York", 8550875);
		map.add("Chicago", 299590);
		map.add("Houston", 2296224);
		map.add("Phoenix", 1448798);
		map.add("San Diego", 1309001);
	}

}
