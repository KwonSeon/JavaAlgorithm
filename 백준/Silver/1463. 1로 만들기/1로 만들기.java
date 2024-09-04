import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] dp = new int[n + 1];
		if (n > 1)
			dp[2] = 1;
		if (n > 2)
			dp[3] = 1;
		for (int i = 4; i <= n; i++) {
			if (i % 2 == 0 && i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3], dp[i / 2]);
				dp[i] = Math.min(dp[i], dp[i - 1]) + 1;
				continue;
			} else if (i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
				continue;
			} else if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
				continue;
			} else {
				dp[i] = 1 + dp[i - 1];
			}
		}

		System.out.println(dp[n]);
	}
}