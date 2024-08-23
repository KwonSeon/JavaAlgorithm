import java.io.*;
import java.util.*;

/**
 * 메모리: 88,132KB, 시간: 355ms
 */

public class Solution {

	static int d, w, k, minChemicals, film[][], A[], B[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			film = new int[d][w];
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minChemicals = Integer.MAX_VALUE;
			A = new int[w];
			B = new int[w];
			Arrays.fill(B, 1);

			if (impactTest()) {
				minChemicals = 0;
			} else {
				subset(0, 0);
			}

			sb.append("#").append(t).append(" ").append(minChemicals).append('\n');
		}

		System.out.println(sb.toString());
	}

	/**
	 * 부분집합 구하기
	 * 
	 * @param index 인덱스
	 * @param cnt   사용한 약품의 수
	 */
	public static void subset(int index, int cnt) {


		// 사용 약품의 수가 최소 약품의 보다 많은 경우
		if (cnt >= minChemicals)
			return;

		// 약품을 사용할 곳을 모두 정하고 최소 2개 이상 사용할 때
		if (index == d) {
			if (cnt < 1)
				return;
			if (!impactTest())
				return;

			minChemicals = cnt;

			return;
		}

		// 약품을 사용할 depth의 배열을 저장
		int[] temp = film[index];

		// 약품을 사용하지 않고
		subset(index + 1, cnt);

		// 약품 A를 사용
		film[index] = A;
		subset(index + 1, cnt + 1);

		// 약품 B를 사용
		film[index] = B;
		subset(index + 1, cnt + 1);

		// 원래대로 되돌리기
		film[index] = temp;

	}

	/**
	 * 충격 실험
	 * 
	 * @param matrix 테스트 할 배열
	 * @return 통과 true, 실패 false
	 */
	public static boolean impactTest() {
		if (k == 1)
			return true;

		// 위에서부터 아래로 확인
		for (int j = 0; j < w; j++) {
			boolean pass = false;
			int cnt = 1;
			for (int i = 1; i < d; i++) {
				// 이전과 특성이 같으면
				if (film[i][j] == film[i - 1][j]) {
					cnt++;
					// 통과시
					if (cnt == k) {
						pass = true;
						break;
					}
					// 특성이 다르면 초기화
				} else {
					cnt = 1;
				}
			}
			// 통과하지 못했다면
			if (!pass)
				return false;
		}

		// 통과했다면
		return true;

	}
}