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
		planting(0, visited, 0);

		System.out.println(minCost);
	}

	public static void planting(int count, boolean[][] visited, int currentCost) {

		// 씨앗 3개를 다 심으면
		if (count == 3) {
			minCost = Math.min(minCost, currentCost);
			return;
		}

		// 씨앗 심기
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (visited[i][j]) continue;

				// 씨앗을 심을 수 있는지 체크
				int cost = calculateCost(i, j, visited);
				if (cost != -1) {
					// 씨앗 심기
					visit(i, j, visited, true);
					planting(count + 1, visited, currentCost + cost);

					// 씨앗 심은 상태 복원
					visit(i, j, visited, false);
				}
			}
		}
	}

	// 씨앗 심을 수 있는지 여부와 비용 계산
	public static int calculateCost(int r, int c, boolean[][] visited) {
		int cost = flowerBed[r][c];
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			if (newR < 0 || newR >= n || newC < 0 || newC >= n || visited[newR][newC]) {
				return -1; // 심을 수 없는 경우
			}
			cost += flowerBed[newR][newC];
		}
		return cost;
	}

	// 방문 상태를 설정하거나 복원
	public static void visit(int r, int c, boolean[][] visited, boolean state) {
		visited[r][c] = state;
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			visited[newR][newC] = state;
		}
	}
}