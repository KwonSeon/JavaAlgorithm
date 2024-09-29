import java.io.*;
import java.util.*;

public class Main {

	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (map.containsKey(input))
				map.put(input, map.get(input) + 1);
			else
				map.put(input, 1);
		}

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (map.containsKey(input))
				sb.append(map.get(input)).append(" ");
			else
				sb.append(0).append(" ");
		}

		System.out.println(sb.toString());
	}
}