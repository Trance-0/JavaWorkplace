package C14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H00960 {
	private int length;

	public H00960() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken()) + 1;
	}

	public static void main(String[] args) throws IOException {
		H00960 a = new H00960();
	}
}
