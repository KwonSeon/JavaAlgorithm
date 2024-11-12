import java.io.*;
import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

			if (input==0){
				if (pq.isEmpty()){
					sb.append(0).append('\n');
				} else {
					sb.append(pq.poll()).append('\n');
				}
			}else {
				pq.offer(input);
			}
		}

		System.out.println(sb.toString());
	}
}