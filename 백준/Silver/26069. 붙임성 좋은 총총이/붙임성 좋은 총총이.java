import java.io.*;
import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		HashSet<String> set = new HashSet<>();
		set.add("ChongChong");

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String person1 = st.nextToken();
			String person2 = st.nextToken();

			if (set.contains(person1) || set.contains(person2)) {
				set.add(person1);
				set.add(person2);
			}
		}

		System.out.println(set.size());
	}
}