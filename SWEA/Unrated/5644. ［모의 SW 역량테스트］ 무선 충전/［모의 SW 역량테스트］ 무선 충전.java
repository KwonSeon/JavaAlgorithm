import java.io.*;
import java.util.*;

public class Solution {

	static int m, a, maxAmount;
	static int[] locationA, locationB, movementInfoA, movementInfoB;
	static int[][] bc;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			// 이동, 차저수
			st = new StringTokenizer(br.readLine());
			// 이동 카운트
			m = Integer.parseInt(st.nextToken());
			// 차저 수
			a = Integer.parseInt(st.nextToken());
			// 사용자 a의 이동 정보
			movementInfoA = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				movementInfoA[i] = Integer.parseInt(st.nextToken());
			}
			// 사용자 b의 이동 정보
			movementInfoB = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				movementInfoB[i] = Integer.parseInt(st.nextToken());
			}
			// bc 정보
			bc = new int[a][];
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				// c, r로 입력되므로 변경 필요
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				// 행, 열, 범위, 파워
				bc[i] = new int[] { r, c, d, p };
			}

			// 위치 초기화
			locationA = new int[] { 1, 1 };
			locationB = new int[] { 10, 10 };

			// 최대 충전량
			maxAmount = charge();

			// 움직이기
			for (int i = 0; i < m; i++) {
				// 이동
				move(movementInfoA[i], movementInfoB[i]);
				// 충전
				maxAmount += charge();
			}

			sb.append("#").append(t).append(" ").append(maxAmount).append('\n');
		}

		System.out.println(sb.toString());
	}

	/**
	 * @param r1 행1
	 * @param c1 열1
	 * @param r2 행2
	 * @param c2 열2
	 * @return 두 지점 사이의 거리
	 */
	public static int distance(int r1, int c1, int r2, int c2) {

		return (Math.abs(r1 - r2) + Math.abs(c1 - c2));
	}

	/**
	 * @return 현재 최대 충전량
	 */
	public static int charge() {

		// 최대 충전 정보
		int charge = 0;

		// 충전 가능한 충전소 인덱스 저장
		boolean[] chargerA = new boolean[a];
		boolean[] chargerB = new boolean[a];

		// 충전 가능한 인덱스 찾기
		for (int i = 0; i < a; i++) {
			// a
			if (distance(locationA[0], locationA[1], bc[i][0], bc[i][1]) <= bc[i][2]) {
				chargerA[i] = true;
			}
			// b
			if (distance(locationB[0], locationB[1], bc[i][0], bc[i][1]) <= bc[i][2]) {
				chargerB[i] = true;
			}
		}

		// 조합을 비교하며 최대 충전을 찾기
		for (int i = 0; i < a; i++) { // a
			for (int j = 0; j < a; j++) { // b
				// 충전량
				int chargeA = 0;
				int chargeB = 0;

				// 충전 여부에 따라 값 지정
				if (chargerA[i])
					chargeA = bc[i][3];
				if (chargerB[j])
					chargeB = bc[j][3];

				// 같은 충전기를 사용할 때 반으로 줄인다
				if (i == j && chargerA[i] && chargerB[i]) {
					charge = Math.max(charge, (chargeA + chargeB) / 2);
					// 충전량 갱신
				} else {
					charge = Math.max(charge, chargeA + chargeB);
				}
			}
		}

		return charge;
	}

	public static void move(int directionA, int directionB) {

		// a 이동
		locationA[0] += dr[directionA];
		locationA[1] += dc[directionA];

		// b 이동
		locationB[0] += dr[directionB];
		locationB[1] += dc[directionB];
	}
}