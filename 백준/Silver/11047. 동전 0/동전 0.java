import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		int[] coins = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		int cnt = 0;
		for (int coin : coins) {
			cnt += m / coin;
			m %= coin;

			if (m == 0) break;

		}
		System.out.println(cnt);
	}

}