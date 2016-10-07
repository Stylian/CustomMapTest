package main;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Runner {

	public Runner() {
	}

	public static void main(String[] args) {
		
		System.out.println("starting test");

		final ForgettingMap<Long, String> map = new ForgettingMap<>(10);
		
		Thread inputWorker1 = new Thread(new MapInputWorker(map));
		Thread inputWorker2 = new Thread(new MapInputWorker(map));
		Thread inputWorker3 = new Thread(new MapInputWorker(map));
		
		
		
		
		
		
		inputWorker1.start();
		inputWorker2.start();
		inputWorker3.start();

	
	}

}
