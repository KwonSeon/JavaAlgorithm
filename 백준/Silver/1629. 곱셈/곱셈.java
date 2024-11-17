import java.io.*;
import java.util.*;

public class Main {

	static int a, b, c, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		System.out.println(modularExponentiation(a, b, c));
	}

	public static long modularExponentiation(long base, long exp, long mod) {

		if (exp == 0) return 1;

		long half = modularExponentiation(base, exp / 2, mod);

		long result = (half * half) % mod;

		if (exp % 2 != 0) {
			result = (result * base) % mod;
		}

		return result;
	}
}