package Genshin;

public class test {
	public static void main(String[] args) {
		String msgString = "/switchPool aklsdfjl";
		System.out
				.print(msgString.substring(msgString.indexOf("/swichPool") + 13, msgString.indexOf("/swichPool") + 17));
	}
}
