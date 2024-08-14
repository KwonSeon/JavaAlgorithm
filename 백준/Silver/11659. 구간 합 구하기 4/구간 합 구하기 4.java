import java.io.*;
import java.util.*;

/**
 * 
 * 시작점부터 끝까지의 값을 dp에 저장 dp[j] - dp[i]으로 범위의 값을 구함
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		// 배열 입력 받기
		int[] dp = new int[n + 1];
		line = br.readLine().split(" ");
		for (int i = 1; i <= n; i++) {
			dp[i] = Integer.parseInt(line[i - 1]);
		}
		// dp 값
		for (int i = 1; i <= n; i++) {
			dp[i] += dp[i - 1];
		}

		// 출력하기
		for (int t = 0; t < m; t++) {
			String[] ij = br.readLine().split(" ");
			int i = Integer.parseInt(ij[0]);
			int j = Integer.parseInt(ij[1]);

			int amount = dp[j] - dp[i - 1];
			sb.append(amount).append('\n');
		}

		System.out.println(sb.toString());
	}
}