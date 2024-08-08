import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			int count = 0;

			// 구분을 위한 index
			Queue<Integer> index = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				index.add(i);
			}

			// 중요도 queue
			Queue<Integer> q = new ArrayDeque<>();
			String[] imp = br.readLine().split(" ");
			for (int i = 0; i < imp.length; i++) {
				q.add(Integer.parseInt(imp[i]));
			}

			while (!q.isEmpty()) {
				int num = index.poll();
				int impNum = q.poll();
				int temp = 0;

				// 뒤에 중요도가 높은 수가 있는지 체크
				for (int i = 0; i < q.size(); i++) {
					int check = q.poll();
					if (check > impNum && check > temp) {
						temp = check;
					}
					q.add(check);
				}

				// 뒤에 중요도가 높은 수가 있으면 뒤로 이동
				if (temp > impNum) {
					index.add(num);
					q.add(impNum);
					// 뒤에 중요도가 높은 수가 없으면 출력
				} else {
					count++;
					if (num == m) {
						break;
					}
				}
				
			}

			sb.append(count).append("\n");
		}

		System.out.println(sb.toString().trim());
	}
}
