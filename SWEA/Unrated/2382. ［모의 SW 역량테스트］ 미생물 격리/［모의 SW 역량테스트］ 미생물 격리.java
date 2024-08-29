import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, k, cnt;
	static Microbe[] microbe;
	static ArrayList<Integer> microbeIndex;

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	static class Microbe {
		int r;
		int c;
		int unit;
		int dir;

		public Microbe(int r, int c, int unit, int dir) {
			this.r = r;
			this.c = c;
			this.unit = unit;
			this.dir = dir;
		}

		public void move() {
			if (this.unit == 0)
				return;
			this.r += dr[dir];
			this.c += dc[dir];
			if (r == 0 || r == n - 1 || c == 0 || c == n - 1) {
				this.unit /= 2;
				switch (this.dir) {
				case 1:
					this.dir = 2;
					break; // 상 -> 하
				case 2:
					this.dir = 1;
					break; // 하 -> 상
				case 3:
					this.dir = 4;
					break; // 좌 -> 우
				case 4:
					this.dir = 3;
					break; // 우 -> 좌
				}
			}
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
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 미생물
			microbe = new Microbe[k];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int unit = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				microbe[i] = new Microbe(r, c, unit, dir);
			}

			while (m > 0) {

				// 미생물 이동
				for (Microbe m : microbe) {
					m.move();
				}

				// 미생물 합치기
				merge();

				m--;
			}

			cnt = count();

			sb.append("#").append(t).append(" ").append(cnt).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void merge() {

		// 정렬
		microbeSorting();
		// 합칠 미생물의 인덱스 저장
		microbeIndex = new ArrayList<Integer>();
		microbeIndex.add(0);

		for (int i = 1; i < k; i++) {
			if (microbe[i].unit == 0)
				continue;
			// 같은 위치의 미생물이면 저장
			if (microbe[microbeIndex.get(microbeIndex.size() - 1)].r == microbe[i].r
					&& microbe[microbeIndex.get(microbeIndex.size() - 1)].c == microbe[i].c) {
				microbeIndex.add(i);
				// 다른 위치의 미생물일 때
			} else {
				// 저장한 미생물 중 겹치는 경우가 없으면 통과
				if (microbeIndex.size() < 2) {
					// 미생물의 인덱스 초기화
					microbeIndex = new ArrayList<Integer>();

					// 인덱스에 현재 미생물 인덱스 넣기
					microbeIndex.add(i);
					continue;
				}
				// 저장한 미생물 중 겹치는 경우 합치기
				int temp = 0;
				int maxUnit = 0;
				int idx = -1;
				for (int index : microbeIndex) {
					Microbe m = microbe[index];
					// 가장 미생물 수가 많은 군집 갱신
					if (m.unit > maxUnit) {
						maxUnit = m.unit;
						idx = index;
					}
					// 임시 총합
					temp += m.unit;
					m.unit = 0;
				}

				// 미생물 수 합치기
				if (idx != -1)
					microbe[idx].unit = temp;

				// 미생물의 인덱스 초기화
				microbeIndex = new ArrayList<Integer>();

				// 인덱스에 현재 미생물 인덱스 넣기
				microbeIndex.add(i);
			}
		}

		// 확인이 끝나고 같은 위치의 미생물이 존재하는 경우
		if (microbeIndex.size() > 1) {
			// 저장한 미생물 중 겹치는 경우 합치기
			int temp = 0;
			int maxUnit = 0;
			int idx = -1;
			for (int index : microbeIndex) {
				Microbe m = microbe[index];
				// 가장 미생물 수가 많은 군집 갱신
				if (m.unit > maxUnit) {
					maxUnit = m.unit;
					idx = index;
				}
				// 임시 총합
				temp += m.unit;
				m.unit = 0;
			}

			// 미생물 수 합치기
			microbe[idx].unit = temp;
		}

	}

	public static int count() {
		int cnt = 0;
		for (Microbe m : microbe) {
			cnt += m.unit;
		}

		return cnt;
	}

	public static void microbeSorting() {

		Arrays.sort(microbe, (Microbe m1, Microbe m2) -> {
			if (m1.r < m2.r) {
				return 1;
			} else if (m1.r > m2.r) {
				return -1;
			} else {
				if (m1.c < m2.c) {
					return 1;
				} else if (m1.c > m2.c) {
					return -1;
				}
			}
			return 0;
		});
	}

}