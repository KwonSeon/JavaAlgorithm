import java.io.*;
import java.util.*;

public class Main {

	static int t, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		t = Integer.parseInt(st.nextToken());

		dp = new int[1001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 2;
		for (int i = 4; i <= 1000; i++) {
			dp[i] = dp[i - 2] + dp[i / 2];
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken())]).append('\n');
		}

		System.out.println(sb.toString());
	}

}