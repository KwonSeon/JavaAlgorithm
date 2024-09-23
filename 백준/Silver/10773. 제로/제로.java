import java.io.*;
import java.util.*;

public class Main {

	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		k = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int input = Integer.parseInt(st.nextToken());

			if (input == 0) stack.pop();
			else stack.add(input);
		}

		int result = 0;
		for (int i : stack) {
			result += i;
		}

		System.out.println(result);
	}
}