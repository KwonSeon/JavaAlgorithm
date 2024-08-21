import java.io.*;
import java.util.*;

/**
 * 메모리: 31,976KB, 시간: 540ms
 */
public class Main {

	static int n, m, r, matrix[][];
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int currentR, currentC, direction, arrLenth, temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				matrix[i][j] = num;
			}
		}

		rotate(0);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void rotate(int index) {

		if (index >= n / 2 || index >= m / 2)
			return;

		currentR = index;
		currentC = index;
		direction = 0;
		arrLenth = (n - 2 * index == 1 || m - 2 * index == 1) ? (n - 2 * index) * (m - 2 * index)
				: 2 * (n - 2 * index) + 2 * (m - 2 * index) - 4;
		for (int i = 0; i < r % arrLenth; i++) {
			move(index);
		}

		rotate(index + 1);

	}

	public static void move(int index) {
		int temp = matrix[index][index];
		while (true) {
			int newR = currentR + dr[direction];
			int newC = currentC + dc[direction];
			if (newR < index || newR >= n - index || newC < index || newC >= m - index) {
				newR -= dr[direction];
				newC -= dc[direction];
				direction = (direction + 1) % 4;
				newR += dr[direction];
				newC += dc[direction];
			}
			
			int temp2 = temp;
			temp = matrix[newR][newC];

			matrix[newR][newC] = temp2;

			currentR += dr[direction];
			currentC += dc[direction];

			if (currentR == index && currentC == index)
				break;
		}

	}
}