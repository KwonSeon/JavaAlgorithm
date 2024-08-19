import java.io.*;
import java.util.*;

public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int n, flowerBed[][], minCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		flowerBed = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				flowerBed[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[n][n];
		minCost = Integer.MAX_VALUE;
		planting(0, visited);

		System.out.println(minCost);
	}

	public static void planting(int count, boolean[][] visited) {

		// 씨앗 3개를 다 심으면
		if (count == 3) {
			// 비용 체크
			int cost = 0;
			for (int l = 0; l < n; l++) {
				for (int m = 0; m < n; m++) {
					if (visited[l][m]) {
						cost += flowerBed[l][m];
					}
				}
			}
			minCost = Math.min(minCost, cost);
			return;
		}

		// 씨앗 심기
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (visited[i][j]) continue;
				// 씨앗을 심을 수 있으면
				if (search(i, j, visited)) {
					visited[i][j] = true;
					for (int k = 0; k < 4; k++) {
						visited[i + dr[k]][j + dc[k]] = true;
					}
					planting(count + 1, visited);

					// 씨앗 심은 상태 복원
					visited[i][j] = false;
					for (int k = 0; k < 4; k++) {
						visited[i + dr[k]][j + dc[k]] = false;
					}
				}

			}
		}
	}

	public static boolean search(int r, int c, boolean[][] visited) {
		for (int i = 0; i < 4; i++) {
			// 새 위치
			int newR = r + dr[i];
			int newC = c + dc[i];
			// 경계 체크, 못 심는 경우
			if (newR < 0 || newR >= n || newC < 0 || newC >= n || visited[newR][newC]) return false;

		}
		return true;
	}
}