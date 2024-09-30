import java.io.*;
import java.util.*;

public class Main {

	static int n, idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		int num = 666;
		while (idx < n) {
			if (String.valueOf(num).contains("666"))
				idx++;
			if (idx == n)
				System.out.println(num);
			num++;
		}
	}
}