import java.io.*;
import java.util.*;

/**
 * 메모리: 32,636KB, 시간: 165ms
 */
public class Solution {

	static int N, M, C, beehive[][], maxEarning, currentEarning, hive[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			beehive = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					beehive[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 최대값 초기화
			maxEarning = 0;
			// 조합
			for (int i = 0; i < N * N; i++) {
				int r1 = i / N;
				int c1 = i % N;
				// 한 줄을 벗어나는 경우 다음으로
				if (c1 > N - M)
					continue;

				hive = new int[M][2];
				int idx = 0;
				while (idx < M) {
					hive[idx] = new int[] { r1, c1 + idx };
					idx++;
				}
				currentEarning = 0;
				subset(0, 0, 0);
				int maxEarning1 = currentEarning;

				for (int j = i + M; j < N * N; j++) {
					int r2 = j / N;
					int c2 = j % N;
					// 한 줄을 벗어나는 경우 다음으로
					if (c2 > N - M)
						continue;
					// 채취할 꿀통의 인덱스 저장
					hive = new int[M][2];
					idx = 0;
					while (idx < M) {
						hive[idx] = new int[] { r2, c2 + idx };
						idx++;
					}
					currentEarning = 0;
					subset(0, 0, 0);
					int maxEarning2 = currentEarning;

					maxEarning = Math.max(maxEarning, maxEarning1 + maxEarning2);

				}
			}

			sb.append("#").append(t).append(" ").append(maxEarning).append('\n');
		}

		System.out.println(sb);
	}

	public static void subset(int idx, int currentHoney, int current) {

		// 채취할 수 있는 최대 양을 넘으면 return
		if (currentHoney > C)
			return;

		// 최대 양 갱신

		if (idx == M) {
			currentEarning = Math.max(current, currentEarning);
			return;
		}

		// 채취
		int honey = beehive[hive[idx][0]][hive[idx][1]];
		subset(idx + 1, currentHoney + honey, current + honey * honey);
		// 미채취
		subset(idx + 1, currentHoney, current);
	}

}