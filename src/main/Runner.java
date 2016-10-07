package main;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		System.out.println("starting test");

		final ForgettingMap<Integer, String> map = new ForgettingMap<>(10);
		
		Thread inputWorker1 = new Thread(new MapInputWorker(map));
		Thread inputWorker2 = new Thread(new MapInputWorker(map));
		Thread inputWorker3 = new Thread(new MapInputWorker(map));
		Thread inputWorker4 = new Thread(new MapInputWorker(map));
		Thread inputWorker5 = new Thread(new MapInputWorker(map));
		
		Thread searchWorker1 = new Thread(new MapSearchWorker(map));
		Thread searchWorker2 = new Thread(new MapSearchWorker(map));
		
		inputWorker1.start();
		inputWorker2.start();
		inputWorker3.start();
		inputWorker4.start();
		inputWorker5.start();

		searchWorker1.start();
		searchWorker2.start();
	}

}
