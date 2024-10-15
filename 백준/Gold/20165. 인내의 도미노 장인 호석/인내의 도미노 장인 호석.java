import java.io.*;
import java.util.*;

public class Main {

	static int n, m, R, map[][], score;
	static boolean[][] visited;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static HashMap<Character, Integer> hashMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		hashMap = new HashMap<>();
		hashMap.put('E', 3);
		hashMap.put('W', 2);
		hashMap.put('S', 1);
		hashMap.put('N', 0);


		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[n][m];

		for (int t = 0; t < R; t++) {
			st = new StringTokenizer(br.readLine());
			int newR = Integer.parseInt(st.nextToken()) - 1;
			int newC = Integer.parseInt(st.nextToken()) - 1;
			int dir = hashMap.get(st.nextToken().charAt(0));
			dfs(newR, newC, dir);

			st = new StringTokenizer(br.readLine());
			int dR = Integer.parseInt(st.nextToken()) - 1;
			int dC = Integer.parseInt(st.nextToken()) - 1;

			if (visited[dR][dC]) {
				visited[dR][dC] = false;
			}


		}

		sb.append(score).append('\n');
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j]) {
					sb.append("F");
				} else {
					sb.append("S");
				}
				sb.append(" ");
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void dfs(int r, int c, int dir) {

		// 넘어지거나 경계를 벗어난 경우
		if (r < 0 || r >= n || c < 0 || c >= m) return;
		if (visited[r][c]) return;

		// 넘어뜨리고 점수 올리기
		visited[r][c] = true;
		score++;

		// 넘어지는 범위
		int height = map[r][c];

		for (int i = 0; i < height; i++) {
			int nr = r + dr[dir] * i;
			int nc = c + dc[dir] * i;

			dfs(nr, nc, dir);
		}
	}
}