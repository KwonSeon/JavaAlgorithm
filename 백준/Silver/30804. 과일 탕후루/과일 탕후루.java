import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, tanghulu[], fruits[], maxCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		tanghulu = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tanghulu[i] = Integer.parseInt(st.nextToken());
		}

		fruits = new int[10];

		int start = 0;
		int end = 0;
		int cnt = 0;

		while (end < n) {

			if (fruits[tanghulu[end]] == 0)
				cnt++;

			fruits[tanghulu[end]]++;
			end++;

			while (cnt > 2) {
				fruits[tanghulu[start]]--;
				if (fruits[tanghulu[start]] == 0) {
					cnt--;
				}

				start++;
			}

			maxCnt = Math.max(maxCnt, end - start);
		}

		System.out.println(maxCnt);
	}

}