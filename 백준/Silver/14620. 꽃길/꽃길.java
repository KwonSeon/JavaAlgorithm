import java.io.*;
import java.util.*;

/**
 * 메모리: 20,188KB, 시간: 328ms
 */
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

		if (currentCost > minCost) return;

		// 씨앗 3개를 다 심으면
		if (count == 3) {
			minCost = currentCost;
			return;
		}

		// 씨앗 심기
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (visited[i][j]) continue;
				// 씨앗을 심을 수 있으면
				if (search(i, j, visited)) {
					visited[i][j] = true;
					currentCost += flowerBed[i][j];
					for (int k = 0; k < 4; k++) {
						visited[i + dr[k]][j + dc[k]] = true;
						currentCost += flowerBed[i + dr[k]][j + dc[k]];
					}
					planting(count + 1, visited, currentCost);

					// 씨앗 심은 상태 복원
					visited[i][j] = false;
					currentCost -= flowerBed[i][j];
					for (int k = 0; k < 4; k++) {
						visited[i + dr[k]][j + dc[k]] = false;
						currentCost -= flowerBed[i + dr[k]][j + dc[k]];
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