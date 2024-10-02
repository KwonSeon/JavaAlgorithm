import java.io.*;
import java.util.*;

public class Main {

	static int n, waiting[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		waiting = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			waiting[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(waiting);

		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += waiting[i] * (n - i);
		}

		System.out.println(ans);
	}
}