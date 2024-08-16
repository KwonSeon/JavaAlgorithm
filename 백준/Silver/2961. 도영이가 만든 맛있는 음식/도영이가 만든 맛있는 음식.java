import java.io.*;
import java.util.*;

/**
 * 
 * 신맛과 쓴맛을 함께 가지고 부분 집합을 구함
 *
 */

public class Main {

	static int n, S, B, ingredients[][], min_diff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		ingredients = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}

		min_diff = Integer.MAX_VALUE;

		dfs(0, 1, 0, 0);
		System.out.println(min_diff);
	}

	/**
	 * 
	 * @param index  재료 인덱스
	 * @param sour   신맛 곱
	 * @param bitter 쓴맛 합
	 */
	public static void dfs(int index, int sour, int bitter, int cnt) {
		int diff = Math.abs(sour - bitter);

		if (diff < min_diff && cnt > 0) {
			min_diff = diff;
		}

		// 최대치 도달 시 종료
		if (index == n)
			return;

		// 재료를 사용하는 경우
		dfs(index + 1, sour * ingredients[index][0], bitter + ingredients[index][1], cnt + 1);

		// 재료를 사용하지 않는 경우
		dfs(index + 1, sour, bitter, cnt);

	}
}