import java.io.*;
import java.util.*;

public class Main {

	static int n, k, q, m;
	static boolean[] snoozing, student;
	static int[] prefixSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// n: 학생 수, k: 졸고 있는 학생 수, q: 출석 코드를 보낼 학생 수, m: 구간의 수
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		snoozing = new boolean[n + 3];
		student = new boolean[n + 3];
		prefixSum = new int[n + 3];

		// 졸고 있는 학생들 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int snooze = Integer.parseInt(st.nextToken());
			snoozing[snooze] = true;
		}

		// 출석 코드를 받을 학생들 입력 및 출석 상태 업데이트
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			int codeReceiver = Integer.parseInt(st.nextToken());

			if (snoozing[codeReceiver]) continue;

			for (int j = codeReceiver; j <= n + 2; j += codeReceiver) {
				if (!snoozing[j]) {
					student[j] = true;
				}
			}
		}

		// 누적 합 계산 (출석하지 않은 학생 수)
		for (int i = 3; i <= n + 2; i++) {
			prefixSum[i] = prefixSum[i - 1] + (student[i] ? 0 : 1);
		}

		// 구간별 결과 출력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(prefixSum[end] - prefixSum[start - 1]).append("\n");
		}

		System.out.print(sb.toString());
	}
}