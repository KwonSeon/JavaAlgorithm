import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		int[] temp = new int[n];
		combination(n, m, temp, 0, 0);

		System.out.println(sb.toString());
	}

	public static void combination(int n, int r, int[] temp, int index, int start) {

		if (index == r) {
			for (int j = 0; j < r; j++) {
				sb.append(temp[j]).append(" ");
			}
			sb.append('\n');
			return;
		}

		for (int i = start; i < n; i++) {
			temp[index] = i + 1;
			combination(n, r, temp, index + 1, i + 1);

		}
	}
}