import java.io.*;
import java.util.*;

public class Main {

	static int n, m, period, matrix[][];
	static boolean[] isCompleted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		matrix = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[to][from] = 1;
		}

		simulation();

		for (int i = 1; i <= n; i++) {
			sb.append(matrix[i][0]).append(" ");
		}

		System.out.println(sb);
	}

	public static void simulation() {
		isCompleted = new boolean[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			boolean offer = true;
			for (int j = 1; j <= n; j++) {
				if (matrix[i][j] == 1) {
					offer = false;
					break;
				}
			}
			if (offer) {
				q.offer(i);
				isCompleted[i] = true;
				matrix[i][0] = 1;
			}
		}

		period = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			boolean[] temp = new boolean[n + 1];
			while (size-- > 0) {
				int current = q.poll();

				for (int i = 1; i <= n; i++) {
					if (matrix[i][current] == 1) {
						boolean possible = true;
						for (int j = 1; j <= n; j++) {
							if (matrix[i][j] == 1) {
								if (!isCompleted[j]) {
									possible = false;
									break;
								}
							}
						}

						if (possible) {
							q.offer(i);
							temp[i] = true;
							if (matrix[i][0] == 0)
								matrix[i][0] = period+2;
						}
					}
				}

			}

			for (int i = 1; i <= n; i++) {
				if (temp[i])
					isCompleted[i] = true;
			}

			period++;
		}

		for (int i = 1; i <= n; i++) {
			if (!isCompleted[i]) {
				period = -1;
				return;
			}
		}
	}
}