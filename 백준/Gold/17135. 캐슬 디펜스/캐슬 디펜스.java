import java.io.*;
import java.util.*;

public class Main {

	static int n, m, d, maxCnt, cnt, originMap[][], map[][], currentSize;
	static ArrayList<int[]> archerList;
	static HashSet<List<Integer>> enemies;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		// originMap
		originMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 궁수 배열 저장
		archerList = new ArrayList<int[]>();
		int[] archers = new int[3];
		combination(0, 0, archers);

		// 궁수 조합만큼 반복
		for (int i = 0; i < archerList.size(); i++) {
			// 현재 궁수 위치
			int[] archer = archerList.get(i);
			// 사용할 맵 복사
			makeMap();

			// cnt 초기화
			cnt = 0;

			// map 크기만큼 반복, 사용할 map의 최대행
			currentSize = n;
			while (currentSize > 0) {
				// 적 찾기
				findEnemy(archer);
				// 적 제거
				for (List<Integer> enemy : enemies) {
					int r = enemy.get(0);
					int c = enemy.get(1);
					map[r][c] = 0;
					cnt++;
				}
				// 다음 단계로 이동
				currentSize--;
			}

			// 삭제한 적들의 수 최대값
			maxCnt = Math.max(maxCnt, cnt);
		}

		System.out.println(maxCnt);
	}

	public static void combination(int index, int start, int[] archers) {

		if (index == 3) {
			archerList.add(archers.clone());
			return;
		}

		for (int i = start; i < m; i++) {
			archers[index] = i;
			combination(index + 1, i + 1, archers);
		}
	}

	/**
	 * 3번 반복해서 가장 가까운 적을 찾고 set에 저장
	 * 
	 * @param archerIndex 궁수 인덱스
	 */
	public static void findEnemy(int[] archerIndex) {
		enemies = new HashSet<>(3);
		for (int a = 0; a < 3; a++) {
			// 1의 범위부터 탐색
			int currentD = 1;
			while (currentD <= d) {
				// 적 발견여부
				boolean find = false;
				// 좌측 하단부터 탐색
				// 현재 범위로 탐색범위 최적화
				for (int j = archerIndex[a] - (currentD - 1); j <= archerIndex[a] + (currentD - 1); j++) {
					for (int i = currentSize - 1; i > currentSize - 1 - currentD; i--) {
						// map 범위를 벗어나는 경우 continue
						if (i < 0 || i >= currentSize || j < 0 || j >= m)
							continue;
						// 공격범위를 벗어나는 경우 반복문 종료, 아래에서부터 탐색하기에 범위를 벗어나면 종료해도 됨
						if (distance(currentSize, archerIndex[a], i, j) > currentD)
							break;
						// 적 발견 시
						if (map[i][j] == 1) {
							// 발견 표시
							find = true;
							// 적 위치 넣기
							enemies.add(Arrays.asList(i, j));
							break;
						}
					}
					// 적 발견시 탐색 종료
					if (find)
						break;
				}
				// 적 발견시 탐색 종료
				if (find)
					break;
				// 범위 증가 후 탐색
				currentD++;
			}
		}
	}

	public static void makeMap() {
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = originMap[i][j];
			}
		}
	}

	public static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
}