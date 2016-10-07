package main;


/**
 *  simulates putting contents to the map 
 *
 */
public class MapInputWorker implements Runnable {

	private ForgettingMap<Integer, String> map;
	
	public MapInputWorker(ForgettingMap<Integer, String> map) {
		this.map = map;
	}

	@Override
	public void run() {

		ContentGenerator generator = ContentGenerator.getInstance();
		
		for(int index = 0; index < 1000; index++) {
			String content = generator.getRandomContent();
			map.add(content.hashCode(), content);
			
			try {
			    Thread.sleep(30);	// worker puts a new content every 30ms
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}

}
