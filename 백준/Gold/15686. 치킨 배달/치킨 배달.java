import java.io.*;
import java.util.*;

public class Main {

	static int n, m, houseLength, chickenLength, minChickenDistance;
	static boolean[] isSelected;

	static House[] house;
	static ArrayList<Chicken> chicken;

	static class House {
		int r;
		int c;
		int[] distance;

		public House(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static class Chicken {
		int r;
		int c;

		public Chicken(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public int distance(int r, int c) {
			return Math.abs(this.r - r) + Math.abs(this.c - c);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		houseLength = 0;
		house = new House[2 * n];
		chicken = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					house[houseLength++] = new House(i, j);
				} else if (input == 2) {
					chicken.add(new Chicken(i, j));
				}
			}
		}

		chickenLength = chicken.size();
		// 집 - 치킨집 사이의 거리 전부 저장
		for (int i = 0; i < houseLength; i++) {
			House h = house[i];
			h.distance = new int[chickenLength];
			for (int j = 0; j < chickenLength; j++) {
				h.distance[j] = chicken.get(j).distance(h.r, h.c);
			}
		}

		isSelected = new boolean[chickenLength];
		minChickenDistance = Integer.MAX_VALUE;
		combination(0, 0);

		System.out.println(minChickenDistance);
	}

	public static void combination(int idx, int start) {

		// 남길 치킨집을 전부 고르면
		if (idx == m) {
			int currentChickenDistance = 0;
			// 집에서 치킨집까지의 거리 찾기
			for (int l = 0; l < houseLength; l++) {
				House h = house[l];
				// 가장 가까운 치킨집 찾기
				int minDistance = Integer.MAX_VALUE;
				for (int j = 0; j < chickenLength; j++) {
					// 뽑히지 않았으면 패스
					if (!isSelected[j])
						continue;
					// 가장 가까운 치킨집과의 거리 갱신
					minDistance = Math.min(minDistance, h.distance[j]);
				}
				currentChickenDistance += minDistance;
			}

			minChickenDistance = Math.min(minChickenDistance, currentChickenDistance);
			return;
		}

		for (int i = start; i < chickenLength; i++) {
			isSelected[i] = true;
			combination(idx + 1, i + 1);
			isSelected[i] = false;
		}
	}
}