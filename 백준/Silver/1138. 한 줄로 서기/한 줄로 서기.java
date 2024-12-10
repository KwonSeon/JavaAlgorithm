import java.io.*;
import java.util.*;

public class Main {

	static int n, array[], record[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		array = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}

		record = new int[n];
		Arrays.fill(record, -1);

		for (int i = 0; i < n; i++) {
			int cnt = array[i];
			for (int j = 0; j < n; j++) {
				if (record[j] != -1) continue;
				if (cnt == 0) {
					record[j] = i + 1;
					break;
				}
				cnt--;
			}
		}

		for (int r : record) {
			sb.append(r).append(" ");
		}

		System.out.println(sb);

	}


}