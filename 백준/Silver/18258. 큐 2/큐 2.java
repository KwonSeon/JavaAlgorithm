import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Queue<Integer> q = new LinkedList<>();

		int n = Integer.parseInt(br.readLine());

		int lastNum = 0;
		for (int i = 0; i < n; i++) {
			String[] command = br.readLine().split(" ");

			switch (command[0]) {
				case "push":
					q.offer(Integer.parseInt(command[1]));
					lastNum = Integer.parseInt(command[1]);
					break;
				case "pop":
					sb.append(q.isEmpty() ? -1 : q.poll()).append('\n');
					break;
				case "size":
					sb.append(q.size()).append('\n');
					break;
				case "empty":
					sb.append(q.isEmpty() ? 1 : 0).append('\n');
					break;
				case "front":
					sb.append(q.isEmpty() ? -1 : q.peek()).append('\n');
					break;
				case "back":
					sb.append(q.isEmpty() ? -1 : lastNum).append('\n');
					break;
			}
		}

		System.out.println(sb);
	}
}
