import java.io.*;
import java.util.*;

public class Main {

	static int n, scores[], sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		int eliminated = (int) Math.round(n * 0.15);

		scores = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			scores[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(scores);

		for (int i = 0; i < eliminated; i++) {
			scores[i] = 0;
			scores[n - 1 - i] = 0;
		}

		for (int score:scores){
			sum += score;
		}

		int score = Math.round((float) sum /(n-2*eliminated));

		System.out.println(score);
	}
}