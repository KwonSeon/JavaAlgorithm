import java.io.*;
import java.util.*;

public class Main {

	static int n, k, cnt, cables[] ;
	static long left, right, mid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		cables = new int[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			cables[i] = Integer.parseInt(st.nextToken());
			if (cables[i] > right) right = cables[i];
		}

		left = 1;

		while (left <= right) {
			cnt = 0;
			mid = left + (right - left) / 2;

			for (int cable : cables) {
				cnt += cable / mid;
			}

			if (cnt >= n) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		System.out.println(right);
	}
}