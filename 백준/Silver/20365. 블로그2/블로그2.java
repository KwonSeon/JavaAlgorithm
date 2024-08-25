import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		String[] line = br.readLine().split("");
		String temp = line[0];
		int cnt = 0;
		for (int i = 1; i < n; i++) {
			if (line[i].equals(temp)) continue;
			temp = line[i];
			cnt++;
		}

		System.out.println((cnt + 1) / 2 + 1);
	}
}