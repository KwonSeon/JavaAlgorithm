import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static char[][] matrix, blindMatrix;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		// 정상인과 색맹 배열
		matrix = new char[n][n];
		blindMatrix = new char[n][n];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				matrix[i][j] = line[j];
				if (line[j] == 'G') {
					blindMatrix[i][j] = 'R';
				} else {
					blindMatrix[i][j] = line[j];
				}
			}
		}

		// 정상인
		int cnt1 = 0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!dfs(i, j, matrix[i][j]))
					continue;
				cnt1++;
			}
		}

		// 색맹
		matrix = blindMatrix;
		int cnt2 = 0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!dfs(i, j, matrix[i][j]))
					continue;
				cnt2++;
			}
		}

		sb.append(cnt1).append(" ").append(cnt2);

		System.out.println(sb.toString());
	}

	public static boolean dfs(int r, int c, char color) {

		// 이미 방문했거나 현재 색과 다른 경우
		if (visited[r][c] || matrix[r][c] != color)
			return false;

		// 방문 처리
		visited[r][c] = true;

		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 경계 체크
			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				continue;
			dfs(nr, nc, color);
		}

		return true;
	}

}