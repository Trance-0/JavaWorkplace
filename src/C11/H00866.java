package C11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H00866 {
	boolean pos[];
	boolean v1[];
	boolean v2[];
	int input;
	int result;

	public H00866() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Integer.parseInt(br.readLine());
		while (input != 0) {
			result = 0;
			pos = new boolean[input + 1];
			v1 = new boolean[2 * input + 1];
			v2 = new boolean[2 * input + 1];
			search(1);
			System.out.println(result);
			input = Integer.parseInt(br.readLine());
		}
	}

	private void search(int k) {
		// TODO Auto-generated method stub
		if (k == input + 1) {
			result++;
		} else {
			for (int i = 1; i <= input; i++) {
				int x1 = k + i;
				int x2 = k - i + input;
				if (!pos[i] && !v1[x1] && !v2[x2]) {
					v1[x1] = true;
					v2[x2] = true;
					pos[i] = true;
					search(k + 1);
					pos[i] = false;
					v1[x1] = false;
					v2[x2] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		H00866 a = new H00866();
	}
}
