import java.io.*;
import java.util.*;

/**
 * 메모리: 41,964,120KB, 시간: 312ms
 */
public class Main {

	static int R, C, cnt;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			dfs(i, 0);
		}

		for (int i = 0; i < R; i++) {
			if (map[i][C - 1] == 'x')
				cnt++;
		}


		System.out.println(cnt);
	}

	public static boolean dfs(int r, int c) {

		// 파이프 연결을 못할 시
		if (map[r][c] != '.')
			return false;

		map[r][c] = 'x';
		
		// 마지막까지 파이프 연결을 성공할 때
		if (c == C - 1) {
			map[r][c] = 'x';
			return true;
		}

		// 탐색
		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;

			if (dfs(nr, nc)) {
				return true;
			}
		}

		return false;
	}

}