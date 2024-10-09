import java.io.*;
import java.util.*;

public class Main {

	static int n, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		if (n == 1) {
			System.out.println(1);
			return;
		} else if (n == 2) {
			System.out.println(2);
			return;
		}

		dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] % 10007 + dp[i - 2] % 10007;
		}

		System.out.println(dp[n] % 10007);
	}
}