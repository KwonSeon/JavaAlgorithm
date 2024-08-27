import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static long l,  centroid[], prefixSum[];
	static boolean unstable;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		// 무게 중심 배열
		centroid = new long[n];
		for (int i = 0; i < n; i++) {
			centroid[i] = Long.parseLong(st.nextToken());
		}

		// 누적 합 계산(무게 중심)
		prefixSum = new long[n];
		prefixSum[0] = centroid[0];
		for (int i = 1; i < n; i++) {
			prefixSum[i] = centroid[i] + prefixSum[i - 1];
		}

		// 시작 상자 i와 마지막 상자의 인덱스 번호 n-1 사용
		for (int i = 0; i < n - 1; i++) {
			// 안전한 구간
			long start = centroid[i] - l;
			long end = centroid[i] + l;
			// 무게 중심
			double cent = (double) (prefixSum[n - 1] - prefixSum[i]) / (n - 1 - i);
			// 무게 중심이 범위를 벗어나면 불안정함
			if (cent <= start || cent >= end) {
				unstable = true;
				break;
			}
		}

		System.out.println(unstable ? "unstable" : "stable");
	}
}