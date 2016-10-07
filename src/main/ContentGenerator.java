package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class ContentGenerator {

	private static volatile ContentGenerator instance;
	
	private List<String> contents;
	
	public static ContentGenerator getInstance() {
		if (instance != null) {
			return instance;
		}

		synchronized (ContentGenerator.class) {
			if (instance == null) {
				instance = new ContentGenerator();
			}
			return instance;
		}
	}

	private ContentGenerator() {
		
		// make a list of contents
		contents = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get("resources/data.txt"))) {
	        stream.forEach(contents::add);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getRandomContent() {
		return contents.get(ThreadLocalRandom.current().nextInt(0, 30));
	}

}
