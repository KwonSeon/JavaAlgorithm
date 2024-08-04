import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			Stack<String> list = new Stack<>();

			for (String pstring : str) {
				if (pstring.equals("(")) {
					list.add(pstring);
				} else {
					if (!list.isEmpty()) {
						list.pop();
					} else {
						list.add(pstring);
						break;
					}
				}
			}

			System.out.println(list.isEmpty() ? "YES" : "NO");
		}
	}
}
