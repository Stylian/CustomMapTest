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
		
		for(int index = 0; index < 1000; index++) {
			String content = generator.getRandomContent();
			String searchedContent = map.find(content.hashCode());
			
			if(searchedContent != null){
				System.out.println(searchedContent);
			}else {
				System.out.println("content does not exist!");
			}
			
			try {
			    Thread.sleep(70);	// search for a content every 70ms
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
}
