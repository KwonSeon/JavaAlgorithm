import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<String> employees = new TreeSet<>(Collections.reverseOrder());

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String[] log = br.readLine().split(" ");
			if (log[1].equals("enter")) {
				employees.add(log[0]);
			} else {
				employees.remove(log[0]);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (String name : employees) {
			sb.append(name).append("\n");
		}

		System.out.println(sb.toString());
	}
}
