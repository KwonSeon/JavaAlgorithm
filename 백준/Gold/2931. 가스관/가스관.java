import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[] up = { '|', '+', '1', '4' };
	static char[] down = { '|', '+', '2', '3' };
	static char[] left = { '-', '+', '1', '2' };
	static char[] right = { '-', '+', '3', '4' };
	static char[][] map, pipeCheck;
	static int R, C, TR, TC;
	static boolean[] compass; // 상하좌우
	static char target;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		// 지도
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] lines = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = lines[j];

			}
		}

		// 체크 목록 만들기
		pipeCheck = new char[][] { up, down, left, right };

		// 방향 체크 배열
		compass = new boolean[4];

		// 탐색
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				findDirection(i, j);
			}
		}
		findPipe();

		// 출력
		sb.append(TR + 1).append(" ").append(TC + 1).append(" ").append(target);

		System.out.println(sb.toString());
	}

	/**
	 * .으로 들어오는 파이프가 있는지 확인하고 체크
	 */
	public static void findDirection(int r, int c) {

		// 빈칸이 아니면 통과
		if (map[r][c] != '.')
			return;

		boolean find = false;
		// 4방 탐색
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			// 경계 체크
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			// 파이프가 들어오는 방향 확인
			for (int l = 0; l < 4; l++) {
				if (map[nr][nc] != pipeCheck[k][l])
					continue;
				// 들어오는 방향 체크
				compass[k] = true;
				find = true;
				break;
			}
		}
		// 들어오는 파이프가 존재하면 저장
		if (find) {
			TR = r;
			TC = c;
			return;
		}

	}

	/**
	 * 파이프 모양 확정
	 */
	public static void findPipe() {
		// 상하좌우
		if (compass[0] && compass[1] && compass[2] && compass[3]) {
			target = '+';
			// 상하
		} else if (compass[0] && compass[1]) {
			target = '|';
			// 좌우
		} else if (compass[2] && compass[3]) {
			target = '-';
			// 우하
		} else if (compass[1] && compass[3]) {
			target = '1';
			// 우상
		} else if (compass[0] && compass[3]) {
			target = '2';
			// 좌상
		} else if (compass[0] && compass[2]) {
			target = '3';
			// 좌하
		} else if (compass[1] && compass[2]) {
			target = '4';
		}
	}

}