package main;

public class MapInputWorker implements Runnable {

	private ForgettingMap map;
	
	public MapInputWorker(ForgettingMap map) {
		this.map = map;
	}

	@Override
	public void run() {

		ContentGenerator generator = ContentGenerator.getInstance();
		
		for(int index = 0; index < 1000; index++) {
			String content = generator.getRandomContent();
			map.add(content.hashCode(), content);
			System.out.println(content);
		}
	}

}
