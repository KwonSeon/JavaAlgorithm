import java.io.*;
import java.util.*;

public class Solution {

	static int n, b, assistants[], minHeightDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			assistants = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				assistants[i] = Integer.parseInt(st.nextToken());
			}
			minHeightDiff = Integer.MAX_VALUE;
			subset(0, 0);

			sb.append("#").append(t).append(" ").append(minHeightDiff).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void subset(int index, int currentHeight) {

		// 높이 차이 업데이트
		if (index == n) {
			if (currentHeight < b)
				return;
			int heightDiff = Math.abs(b - currentHeight);
			minHeightDiff = Math.min(minHeightDiff, heightDiff);
			return;
		}

		subset(index + 1, currentHeight + assistants[index]);
		subset(index + 1, currentHeight);

	}
}