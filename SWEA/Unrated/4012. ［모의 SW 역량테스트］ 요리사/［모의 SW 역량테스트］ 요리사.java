import java.io.*;
import java.util.*;

public class Solution {

	static int n, ingredients[][], temp[], taste, temp2[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= tc; t++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			ingredients = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					ingredients[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 뽑은 수 체크
			boolean[] isSelected = new boolean[n];
			// 뽑은 수
			temp = new int[n / 2];
			// 만족도
			taste = Integer.MAX_VALUE;
			combination(0, 0, isSelected);

			sb.append("#").append(t).append(" ").append(taste).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void combination(int index, int start, boolean[] isSelected) {

		if (index == n / 2) {
			temp2 = new int[n / 2];
			int idx = 0;
			for (int j = 0; j < n; j++) {
				if (!isSelected[j])
					temp2[idx++] = j;
			}
			// 맛 차이 계산
			int dish1 = 0;
			int dish2 = 0;
			for (int j = 0; j < n / 2; j++) {
				for (int k = 0; k < n / 2; k++) {
					// 같은 재료 넘어가
					if (j == k)
						continue;

					// 맛 합 구하기
					dish1 += ingredients[temp[j]][temp[k]];
					dish2 += ingredients[temp2[j]][temp2[k]];
				}
			}
			int tasteDiff = Math.abs(dish1 - dish2);
			taste = Math.min(taste, tasteDiff);

			return;
		}

		for (int i = start; i < n; i++) {
			isSelected[i] = true;
			temp[index] = i;
			combination(index + 1, i + 1, isSelected);
			isSelected[i] = false;
		}
	}
}