import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		String[] counts = br.readLine().split(" ");
		int[] balloons = new int[n];
		boolean[] popped = new boolean[n];

		for (int i = 0; i < n; i++) {
			balloons[i] = Integer.parseInt(counts[i]);
		}

		int index = 0;
		int remaining = n;

		while (remaining > 0) {
			sb.append(index + 1).append(" ");
			popped[index] = true;
			if (--remaining == 0) break;

			int moves = balloons[index];

			int step = Math.abs(moves) % remaining;
			if (step == 0) step = remaining;


			if (moves > 0) {
				while (step > 0) {
					index = (index + 1) % n;
					if (!popped[index]) step--;
				}
			} else {
				while (step > 0) {
					index = (index - 1 + n) % n;
					if (!popped[index]) step--;
				}
			}
		}

		System.out.println(sb.toString());
	}
}
