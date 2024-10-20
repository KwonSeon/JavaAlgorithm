import java.io.*;
import java.util.*;

public class Main {

	static int n, m, height[], maxHeight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		height = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(maxHeight, height[i]);
		}

		Arrays.sort(height);

		int h = binarySearch();

		System.out.println(h);


	}

	public static int binarySearch() {

		int start = 0;
		int end = maxHeight;
		int ans = 0;

		while (start <= end) {
			int mid = (start + end) / 2;

			long cnt = 0;
			for (int h : height) {
				if (h > mid) {
					cnt += (h - mid);
				}
			}

			if (cnt >= m) {
				ans = mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return ans;
	}
}