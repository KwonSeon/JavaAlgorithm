import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		boolean[] check_arr = new boolean[n+1];
		int[] arr = new int[m];

		permutation(n, m, check_arr, 0, arr);

		System.out.println(sb.toString());
	}

	public static void permutation(int n, int r, boolean[] check, int depth, int[] arr) {
		if (depth == r) {
			for (int j = 0; j < r; j++) {
				sb.append(arr[j]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (check[i]) continue;
			
			check[i] = true;
			arr[depth] = i;
			
			permutation(n, r, check, depth + 1, arr);
			
			check[i] = false;
		}
	}
}