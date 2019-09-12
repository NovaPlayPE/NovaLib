package net.novaplay.library.physics;

import lombok.Getter;
import lombok.Setter;

public class Speed {
	
	@Getter
	@Setter
	public float time = 0; //s
	@Getter
	@Setter
	public float destination = 0; //m
	@Getter
	@Setter
	public float speed = 0; // m/s
	
	
	public float calculateSpeed() {
		try {
			return (destination/time);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return 0; //no, there shouln't be null
		}
	}
	
	public float calculateDestination() {
		try {
			return (speed*time);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return 0; //no, there shouln't be null
		}
	}
	
	public float calculateTime() {
		try {
			return (destination/speed);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			return 0; //no, there shouln't be null
		}
	}

}
