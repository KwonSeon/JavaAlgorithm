import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k, map[][], cnt;
	static ArrayList<Integer> area;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int area = 0; area < k; area++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			for (int i = n - 1 - r1; i > n - 1 - r2; i--) {
				for (int j = c1; j < c2; j++) {
					map[i][j] = 1;
				}
			}
		}

		area = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cnt = 0;
				dfs(i, j);
				if (cnt != 0) {
					area.add(cnt);
				}
			}
		}

		area.sort((o1, o2) -> o1 - o2);
		sb.append(area.size()).append('\n');

		for (int a : area) {
			sb.append(a).append(" ");
		}

		System.out.println(sb.toString());
	}

	public static void dfs(int r, int c) {

		if (r < 0 || r >= n || c < 0 || c >= m || map[r][c] == 1)
			return;

		map[r][c] = 1;
		cnt++;

		for (int dir = 0; dir < 4; dir++) {
			dfs(r + dr[dir], c + dc[dir]);
		}

	}
};