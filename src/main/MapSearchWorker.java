package main;

/**
 *	simulates looking for contents in the map 
 *
 */
public class MapSearchWorker implements Runnable {

	private ForgettingMap<Integer, String> map;
	
	public MapSearchWorker(ForgettingMap<Integer, String> map) {
		this.map = map;
	}
	
	@Override
	public void run() {

		ContentGenerator generator = ContentGenerator.getInstance();
		
		for(int index = 0; index < 100; index++) {
			String content = generator.getRandomContent();
			String searchedContent = map.find(content.hashCode());
			
			if(searchedContent != null){
				System.out.println("found " + searchedContent);
			}

			try {
			    Thread.sleep(70);	// search for a content every 70ms
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
