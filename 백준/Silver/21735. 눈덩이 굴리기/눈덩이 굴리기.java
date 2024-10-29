import java.io.*;
import java.util.*;

public class Main {

	static int n, m, snow[], maxSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		snow = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			snow[i] = Integer.parseInt(st.nextToken());
		}

		subset(0, 0, 1);

		System.out.println(maxSize);
	}

	public static void subset(int idx, int time, int size) {

		if (idx == n || time == m) {
			maxSize = Math.max(size, maxSize);
			return;
		}

		if (idx + 1 <= n)
			subset(idx + 1, time + 1, size + snow[idx + 1]);

		if (idx + 2 <= n)
			subset(idx + 2, time + 1, (size / 2) + snow[idx + 2]);
	}
}