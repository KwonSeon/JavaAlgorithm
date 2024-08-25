import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		char[] line = br.readLine().toCharArray();
		char temp = line[0];
		int cnt = 0;
		for (char character : line) {
			if (character == temp) continue;
			temp = character;
			cnt++;
		}

		System.out.println((cnt + 1) / 2 + 1);
	}
}