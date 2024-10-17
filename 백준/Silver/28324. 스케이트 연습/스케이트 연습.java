import java.io.*;
import java.util.*;

public class Main {

	static int n, v[], sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		v = new int[n];


		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			v[i] = Integer.parseInt(st.nextToken());
		}

		v[n - 1] = 1;

		for (int i = n - 2; i >= 0; i--) {
			v[i] = Math.min(v[i], v[i + 1] + 1);
		}

		for (int s : v) {
//			sb.append(s).append(" ");
			sum += s;
		}

//		System.out.println(sb.toString());
		System.out.println(sum);
	}
}
