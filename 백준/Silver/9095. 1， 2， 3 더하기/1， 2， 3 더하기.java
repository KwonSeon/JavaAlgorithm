import java.io.*;
import java.util.*;

public class Main {

	static int n, tc[], dp[], maxNum, target;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		tc = new int[n];

		for (int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			tc[i] = Integer.parseInt(st.nextToken());
			maxNum = Math.max(maxNum, tc[i]);
		}

		dp = new int[maxNum + 1];

		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= maxNum; i++) {
			dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
		}

		for (int i = 0; i < n; i++) {
			sb.append(dp[tc[i]]).append('\n');
		}

		System.out.println(sb.toString());
	}


}