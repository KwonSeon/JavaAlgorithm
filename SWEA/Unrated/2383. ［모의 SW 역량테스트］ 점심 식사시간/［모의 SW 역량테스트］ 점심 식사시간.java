import java.io.*;
import java.util.*;

public class Solution {

	static int n, peopleLength, stairLength, minTime;
	static boolean[] isSelected;

	static People[] people;
	static Stair[] stair;

	static class People {
		int r;
		int c;
		int[] distance;

		public People(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.distance = new int[2];
		}
	}

	static class Stair {
		int r;
		int c;
		int height;

		public Stair(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
		}

		public int distance(int r, int c) {
			return Math.abs(this.r - r) + Math.abs(this.c - c);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			people = new People[10];
			stair = new Stair[2];
			peopleLength = 0;
			stairLength = 0;

			// 입력, 사람과 계단 배열로 받기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int input = Integer.parseInt(st.nextToken());
					if (input == 1) {
						people[peopleLength++] = new People(i, j);
					} else if (input >= 2) {
						stair[stairLength++] = new Stair(i, j, input);
					}
				}
			}

			// 계단까지의 거리 저장
			for (int i = 0; i < peopleLength; i++) {
				People p = people[i];
				p.distance[0] = stair[0].distance(p.r, p.c);
				p.distance[1] = stair[1].distance(p.r, p.c);
			}

			minTime = Integer.MAX_VALUE;
			isSelected = new boolean[peopleLength];
			subset(0);

			sb.append("#").append(t).append(" ").append(minTime).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void subset(int idx) {

		// 사람들이 이동할 계단을 전부 정하면 실행
		if (idx == peopleLength) {
			// 계단 도착 시간
			ArrayList<Integer> arr1 = new ArrayList<>();
			ArrayList<Integer> arr2 = new ArrayList<>();

			// 계단 도착 + 대기시간 저장
			for (int i = 0; i < peopleLength; i++) {
				if (isSelected[i]) {
					arr1.add(people[i].distance[0] + 1);
				} else {
					arr2.add(people[i].distance[1] + 1);
				}
			}

			// 오름차순으로 정렬
			arr1.sort((o1, o2) -> o1 - o2);
			arr2.sort((o1, o2) -> o1 - o2);

//			System.out.println("arr1 : "+arr1.toString());
//			System.out.println("arr2 : "+arr2.toString());

			int time1 = getDownTime(arr1, 0);
//			System.out.println("time1 : "+ time1);
			int time2 = getDownTime(arr2, 1);
//			System.out.println("time2 : "+ time2);

			minTime = Math.min(minTime, Math.max(time1, time2));
			return;
		}

		// 계단 2까지의 거리 + 1 저장
		isSelected[idx] = true;
		subset(idx + 1);

		// 계단 1까지의 거리 + 1 저장
		isSelected[idx] = false;
		subset(idx + 1);

	}

	public static int getDownTime(ArrayList<Integer> times, int s) {

		if (times.isEmpty())
			return 0;

		// 계단을 사용할 사람들 저장
		Queue<Integer> q = new ArrayDeque<Integer>();

		int downTime = stair[s].height;

		for (int arrivalTime : times) {
			// 도착 시간이 현재 시간보다 빠른 경우
			while (!q.isEmpty() && q.peek() <= arrivalTime) {
				q.poll();
			}

			// 계단 사용자가 3명 미만인 경우
			if (q.size() < 3) {
				q.offer(arrivalTime + downTime);
				// 계단 사용자가 3명 이상인 경우
				// 현재 시간을 갱신하고 계단을 다 사용한 이후 사용하도록 추가
			} else {
				int nextTime = q.poll();
				q.offer(nextTime + downTime);
			}
		}

		// 계단을 내려가는 시간 갱신
		int currentTime = 0;
		while (!q.isEmpty()) {
			currentTime = q.poll();
		}

		return currentTime;
	}
}