import java.io.*;
import java.util.*;

public class Main {

	static int n, m, laboratory[][], matrix[][], virusCnt, safeCnt, maxSafeCnt;
	static boolean[][] comb, visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n][m];

		// 초기 연구소 상태
		laboratory = new int[n][m];
		// 1. 벽을 세울 수 있는 위치 저장용으로 사용
		// 2. 벽을 세우고 로직을 수행하는 곳으로 사용
		matrix = new int[n][m];
		// 조합용 저장
		comb = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				laboratory[i][j] = num;
				// 벽을 세울 수 있는 위치 저장
				if (num == 0) {
					safeCnt++;
					comb[i][j] = true;
				}
			}
		}

		// matrix를 laboratory로 변경
		for (int i = 0; i < n; i++) {
			System.arraycopy(laboratory[i], 0, matrix[i], 0, m);
		}


		// 벽 세우는 것을 고려하여 안전지대 개수 수정
		safeCnt -= 3;

		combination(0, 0);

		System.out.println(maxSafeCnt);

	}

	public static void combination(int cnt, int start) {

		if (cnt == 3) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (matrix[i][j] == 2) {
						dfs(i, j);
					}
				}
			}

			maxSafeCnt = Math.max(maxSafeCnt, safeCnt - virusCnt);
			virusCnt = 0;
			visited = new boolean[n][m];
			return;
		}

		for (int idx = start; idx < n * m; idx++) {
			int r = idx / m;
			int c = idx % m;

			if (!comb[r][c])
				continue;
			matrix[r][c] = 1;

			combination(cnt + 1, idx + 1);
			matrix[r][c] = 0;
		}

	}

	public static void dfs(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m || matrix[r][c] == 1 || visited[r][c])
			return;

		if (matrix[r][c] != 2) {
			virusCnt++;
		}

		visited[r][c] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			dfs(nr, nc);
		}
	}

}