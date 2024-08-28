import java.io.*;
import java.util.*;

public class Solution {

	static int n, start, matrix[][], maxNum, cnt;
	static boolean called[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 인접행렬 만들기
			matrix = new int[101][101];
			for (int i = 0; i < n / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				matrix[from][to] = 1;
			}
			// 연락 여부
			called = new boolean[101];
			bfs();

			sb.append("#").append(t).append(" ").append(maxNum).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		called[start] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			maxNum = 0;
			for (int q = 0; q < size; q++) {
				int current = queue.poll();
				for (int i = 1; i <= 100; i++) {
					if (matrix[current][i] == 0 || called[i] == true)
						continue;
					// 연락 처리
					called[i] = true;
					// queue에 넣기
					queue.add(i);

				}

				// 최고 번호 갱신
				maxNum = Math.max(maxNum, current);
			}

		}
	}
}