package C2;

import java.util.Scanner;

public class NOIP2015P {
	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		int result = 0;
		int days = c.nextInt();
		c.close();
		int index = 1;
		int money = 1;
		int cycle = 1;
		while (index <= days) {
			result += money;
			if (cycle == money) {
				cycle = 0;
				money++;
			}
			cycle++;
			index++;
		}
		System.out.println(result);
	}
}
