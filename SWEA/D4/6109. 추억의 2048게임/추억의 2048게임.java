import java.io.*;
import java.util.*;

public class Solution {

	static int n, commandCnt, matrix[][], limit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			// 회전 수 초기화
			if (command.equals("right")) {
				commandCnt = 2;
			} else if (command.equals("up")) {
				commandCnt = 3;

			} else if (command.equals("down")) {
				commandCnt = 1;
			} else {
				commandCnt = 0;
			}

			// 입력받기
			matrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 회전
			rotate(0, commandCnt);

			// 합치기
			for (int i = 0; i < n; i++) {
				limit = -1;
				for (int j = 1; j < n; j++) {
					move(i, j);
				}
			}

			// 원상태로 회전
			rotate(0, 4-commandCnt );

			sb.append("#").append(t).append('\n');
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(matrix[i][j]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.println(sb.toString());
	}

	// 90도 시계방향 회전
	public static void rotate(int currentCnt, int targetCnt) {

		if (currentCnt == targetCnt)
			return;

		int[][] newMatrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMatrix[i][j] = matrix[n - 1 - j][i];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = newMatrix[i][j];
			}
		}

		rotate(currentCnt + 1, targetCnt);
	}

	// 왼쪽으로 합치기
	public static void move(int r, int c) {

		// 가장 왼쪽이거나 비었거나 왼쪽이 더 큰 경우
		if (c <= limit || c<=0  || matrix[r][c] == 0 || matrix[r][c - 1] > matrix[r][c])
			return;

		// 옆이 0이거나 같은 경우
		if (matrix[r][c - 1] == 0) {
			matrix[r][c - 1] += matrix[r][c];
			matrix[r][c] = 0;
		} else if (matrix[r][c - 1] == matrix[r][c]) {
			matrix[r][c - 1] += matrix[r][c];
			matrix[r][c] = 0;
			limit = c;
		}

		move(r, c - 1);
	}
}