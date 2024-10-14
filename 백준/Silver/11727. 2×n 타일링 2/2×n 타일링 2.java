import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		if (n == 1) {
			System.out.println(1);
			return;
		} else if (n == 2) {
			System.out.println(3);
			return;
		}

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] % 10007 + (2 * dp[i - 2]) % 10007;
		}

		System.out.println(dp[n] % 10007);

	}
}

/**
 * 1 -> 1
 * 2 -> 3
 * 3 -> 5
 * 4 -> 11
 * 5 -> 21
 */