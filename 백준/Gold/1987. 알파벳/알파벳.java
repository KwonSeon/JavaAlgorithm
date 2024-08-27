import java.io.*;
import java.util.*;

public class Main {

	static int R, C, maxCnt;
	static char board[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 체스 보드, 마지막으로 사용한 알파벳
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				board[i][j] = line[j];
			}
		}
		// 알파벳 사용
		boolean[] alphabet = new boolean['Z' + 1];

		// 기물의 위치 R-1, C-1, 현재 사용한 알파벳
		maxCnt = 0;
		dfs(0, 0, alphabet, 1);

		System.out.println(maxCnt);

	}

	public static void dfs(int r, int c, boolean[] alphabet, int cnt) {

		// 이미 사용한 알파벳인 경우
		if (alphabet[board[r][c]])
			return;

		// 알파벳 사용
		alphabet[board[r][c]] = true;
		maxCnt = Math.max(maxCnt, cnt);

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 경계 체크
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			dfs(nr, nc, alphabet, cnt + 1);
		}
		
		// 알파벳 사용 초기화
		alphabet[board[r][c]] = false;

	}
}