import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);

		Queue<Integer> arr = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			arr.add(i);
		}

		ArrayList<Integer> ans = new ArrayList<>();
		while (!arr.isEmpty()) {
			for (int i = 0; i < k - 1; i++) {
				arr.add(arr.poll());
			}

			ans.add(arr.poll());
		}
		System.out.print("<");
		for (int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i));
			if (i == ans.size() - 1) {
				break;
			} else {
				System.out.print(", ");
			}
		}
		System.out.print(">");
	}
}
