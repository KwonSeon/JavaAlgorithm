import java.io.*;
import java.util.*;

public class Main {

	static int n, tip[];
	static long tips;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		tip = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			tip[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(tip);

		for (int i = n - 1; i >= 0; i--) {
			long result = (long) tip[i] - (long) (n - 1 - i);

			if (result <= 0) continue;

			tips += result;
		}

		System.out.println(tips);
	}
}