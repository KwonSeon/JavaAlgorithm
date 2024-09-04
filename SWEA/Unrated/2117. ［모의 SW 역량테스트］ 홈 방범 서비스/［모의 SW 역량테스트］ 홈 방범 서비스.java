import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, k, map[][], earning, matrix[][], cnt, maxCnt;
	static boolean[][] visited;
	static ArrayList<Home> list;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Home {
		int r;
		int c;

		public Home(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			// 집의 주소 저장
			list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						list.add(new Home(i, j));
					}
				}
			}

			maxCnt = 0;
			k = 1;
			while (k <= 2 * n) {
				earning = 0;
				// 사용할 맵
				matrix = new int[n][n];
				// 가장 많이 겹치는 수 갱신
				cnt = 0;
				// 집을 순회하며 맵 바꾸기
				for (Home h : list) {

					overlapping(h, 1);
				}

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						cnt = Math.max(cnt, matrix[i][j]);
					}
				}

				earning = (cnt * m) - (k * k + (k - 1) * (k - 1));
				if (earning >= 0)
					maxCnt = Math.max(cnt, maxCnt);

				k++;
			}

			sb.append("#").append(t).append(" ").append(maxCnt).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void overlapping(Home home, int distance) {
		visited = new boolean[n][n];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { home.r, home.c });
		visited[home.r][home.c] = true;
		matrix[home.r][home.c]++;

		while (distance++ < k) {
			int size = q.size();

			while (size-- > 0) {
				int[] h = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nr = h[0] + dr[dir];
					int nc = h[1] + dc[dir];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc])
						continue;
					matrix[nr][nc]++;
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}

			}
		}

	}
}