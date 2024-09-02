import java.io.*;
import java.util.*;

public class Main {

	static int n, l, d, fastestTime, t;
	static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		int t = 1;
		for (int i = 1; i <= n; i++) {
			int minTime = i * (l + 5) - 5;
			int maxTime = i * (l + 5);
			while (true) {
				if (d * t > minTime - 1 && d * t < maxTime) {
					find = true;
					fastestTime = d * t;
					break;
				}

				if (d * t >= maxTime)
					break;

				t++;
			}

			if (find)
				break;
		}

		if (fastestTime == 0) {
			while (d * t < n * (l + 5) - 5) {
				t++;
			}
			fastestTime = d * t;
		}

		System.out.println(fastestTime);
	}

}