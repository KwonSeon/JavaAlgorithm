import java.io.*;
import java.util.*;

public class Main {

	static int n, k, idx, cnt;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		visited = new boolean[n];
		idx = 0;
		// 남은 청설모 수
		cnt = n;

		// 남은 청설모 수가 k보다 작으면 멈춘다.
		while (cnt >= k) {
			// 제거한 청설모 수 처음 idx 이후부터 제거이므로 1부터 시작
			int temp = 1;
			// k만큼 제거
			while (temp < k) {
				// idx 증가
				idx = (idx + 1) % n;
				// 청설모가 제거 되지 않은 경우
				if (!visited[idx]) {
					visited[idx] = true;
					temp++;
					cnt--;
				}
			}

			// 다음 idx로 이동
			for (int i = 1; i < n; i++) {
				if (visited[(idx + i) % n]) continue;
				idx = (idx + i) % n;
				break;
			}
		}

		System.out.println(idx + 1);
	}
}