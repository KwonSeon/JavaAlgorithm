import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int cnt = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int t = n / 5;

		while (t >= 0) {
			int remainder5 = n - 5 * t;
			int share2 = remainder5 / 2;
			int remainder2 = remainder5 % 2;

			if (remainder2 == 0) {
				cnt = share2 + t;
				break;
			} else {
				t--;
			}
		}

		System.out.println(cnt);
	}
}
