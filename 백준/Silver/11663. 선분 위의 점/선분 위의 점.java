import java.io.*;
import java.util.*;

public class Main {

	static int n, m, dots[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dots = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			dots[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(dots);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int startIdx = findLowerBound(start);
			int endIdx = findUpperBound(end);

			// 범위에 점이 없는 경우 처리
			if (startIdx > endIdx) {
				sb.append(0).append('\n');
			} else {
				sb.append(endIdx - startIdx + 1).append('\n');
			}
		}

		System.out.println(sb);
	}

	public static int findLowerBound(int low) {
		int left = 0;
		int right = dots.length;

		while (left < right) {
			int mid = (left + right) / 2;
			if (dots[mid] >= low) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// end 이하인 마지막 점의 인덱스를 찾는 이진 탐색
	public static int findUpperBound(int high) {
		int left = 0;
		int right = dots.length;

		while (left < right) {
			int mid = (left + right) / 2;
			if (dots[mid] > high) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left - 1;
	}
}