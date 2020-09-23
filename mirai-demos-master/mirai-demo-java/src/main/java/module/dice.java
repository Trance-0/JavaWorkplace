package module;

import java.util.Random;

public class dice {
	private Random rand = new Random();
	private int value;

	public dice() {
		value = rand.nextInt(6) + 1;
	}

	public String toString() {
		return "" + value;
	}
}
