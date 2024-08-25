import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		String line = br.readLine();
		char temp = line.charAt(0);
		int cnt = 0;
		for (int i = 1; i < n; i++) {
			char currentChar = line.charAt(i);
			if (currentChar == temp) continue;
			temp = currentChar;
			cnt++;
		}

		System.out.println((cnt + 1) / 2 + 1);
	}
}