import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, cnt;
	static boolean ingredients[], balance[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			balance = new boolean[n][n];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				balance[a - 1][b - 1] = true;
				balance[b - 1][a - 1] = true;
			}

			ingredients = new boolean[n];
			cnt = 0;
			subset(0);

			sb.append("#").append(t).append(" ").append(cnt).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void subset(int index) {

		if (index == n) {

			for (int i = 0; i < n; i++) {
				// 재료를 사용하지 않으면 넘어감
				if (!ingredients[i])
					continue;
				// 재료를 사용했을 때
				for (int j = 0; j < n; j++) {
					// 사용한 재료가 존재하고 조합이 맞지 않은 경우
					if (ingredients[j] && balance[i][j])
						return;
				}

			}
			cnt++;
			return;
		}

		// 재료 사용
		ingredients[index] = true;
		subset(index + 1);

		// 재료 미사용
		ingredients[index] = false;
		subset(index + 1);
	}
}