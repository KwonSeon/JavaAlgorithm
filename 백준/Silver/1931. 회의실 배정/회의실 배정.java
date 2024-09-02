import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static Meeting[] table;

	static class Meeting {
		int startTime;
		int endTime;

		public Meeting(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		PriorityQueue<Meeting> pq = new PriorityQueue<>(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				return o1.endTime == o2.endTime ? o1.startTime - o2.startTime : o1.endTime - o2.endTime;
			}
		});

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.offer(new Meeting(s, e));
		}


		int cnt = 0;
		int lastEndTime = 0;

		while (n-->0) {
			Meeting meeting = pq.poll();
			if (meeting.startTime >= lastEndTime) {
				lastEndTime = meeting.endTime;
				cnt++;
			}
		}

		System.out.println(cnt);
	}

}