import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int quotient5 = n / 5;
		int remainder5 = n % 5;
		int quotient3 = 0;
		int remainder3 = -1;

		while (quotient5 >= 0) {
			remainder3 = remainder5 % 3;
			if (remainder3 == 0) {
				quotient3 = remainder5 / 3;
				break;
			}
			quotient5 -= 1;
			remainder5 += 5;
			continue;
		}

		int ans = remainder3 == 0 ? quotient5 + quotient3 : -1;
		System.out.println(ans);
	}
}