import java.io.*;
import java.util.*;

public class Solution {

	static int m, a, maxAmount;
	static int[] movementInfoA, movementInfoB;
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };
	static User locationA, locationB;
	static BC[] bc;

	/**
	 * 사용자 위치
	 */
	static class User {
		int r;
		int c;

		/**
		 * 사용자 위치 정보
		 * 
		 * @param r 행
		 * @param c 열
		 */
		public User(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public void move(int dr, int dc) {
			this.r += dr;
			this.c += dc;
		}

	}

	static class BC {
		int r;
		int c;
		int d;
		int p;

		public BC(int r, int c, int d, int p) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.p = p;
		}
	}

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
			bc = new BC[a];
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				// c, r로 입력되므로 변경 필요
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				// 행, 열, 범위, 파워
				bc[i] = new BC(r, c, d, p);
			}

			// 위치 초기화
			locationA = new User(1, 1);
			locationB = new User(10, 10);

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
	 * @param 사용자
	 * @param 배터리차저
	 * @return 두 좌표 사이의 거리
	 */
	public static int distance(User user, BC bc) {

		return (Math.abs(user.r - bc.r) + Math.abs(user.c - bc.c));
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
			if (distance(locationA, bc[i]) <= bc[i].d) {
				chargerA[i] = true;
			}
			// b
			if (distance(locationB, bc[i]) <= bc[i].d) {
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
					chargeA = bc[i].p;
				if (chargerB[j])
					chargeB = bc[j].p;

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
		locationA.move(dr[directionA], dc[directionA]);

		// b 이동
		locationB.move(dr[directionB], dc[directionB]);
	}
}