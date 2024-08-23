import java.io.*;
import java.util.*;

public class Solution {

	static int h, w, tank[], n;
	static char map[][], command[];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			// 탱크 좌표, 상태
			map = new char[h][];
			// 맵 입력
			for (int i = 0; i < h; i++) {
				char[] wArray = br.readLine().toCharArray();
				map[i] = wArray;
			}
			// 탱크 좌표, 상태 저장
			for (int i = 0; i < h; i++) {
				boolean findTank = false;
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						tank = new int[] { i, j, map[i][j] };
						// 맵을 평지로 변경
						map[i][j] = '.';
						// 탱크를 저장하면 종료
						findTank = true;
						break;
					}
					// 탱크를 저장하면 종료
					if (findTank)
						break;
				}
			}
			// command 저장
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			command = br.readLine().toCharArray();
			// 시뮬레이션
			for (char c : command) {
				simulation(c);
			}

			// 탱크 표시
			map[tank[0]][tank[1]] = (char) tank[2];

			// 출력
			sb.append("#").append(t).append(" ");
			for (char[] mapArray : map) {
				for (char m : mapArray) {
					sb.append(m);
				}
				sb.append('\n');
			}
		}

		System.out.println(sb.toString());
	}

	public static void simulation(char command) {
		if (command == 'U') {
			up();
		} else if (command == 'D') {
			down();
		} else if (command == 'L') {
			left();
		} else if (command == 'R') {
			right();
		} else {
			int direction = 0;
			if (tank[2] == '^') {
				direction = 0;
			} else if (tank[2] == 'v') {
				direction = 1;
			} else if (tank[2] == '<') {
				direction = 2;
			} else if (tank[2] == '>') {
				direction = 3;
			}
			shoot(tank[0], tank[1], direction);
		}
	}

	public static void up() {
		int nr = tank[0] - 1;
		int nc = tank[1];
		// 범위를 벗어나거나 평지가 아닌 경우
		if (nr < 0 || map[nr][nc] != '.') {
			// 위로 방향을 변경
			tank = new int[] { tank[0], tank[1], '^' };
		} else {
			// 위로 이동
			tank = new int[] { nr, nc, '^' };
		}
	}

	public static void down() {
		int nr = tank[0] + 1;
		int nc = tank[1];
		// 범위를 벗어나거나 평지가 아닌 경우
		if (nr >= h || map[nr][nc] != '.') {
			// 아래로 방향을 변경
			tank = new int[] { tank[0], tank[1], 'v' };
		} else {
			// 아래로 이동
			tank = new int[] { nr, nc, 'v' };
		}
	}

	public static void left() {
		int nr = tank[0];
		int nc = tank[1] - 1;
		// 범위를 벗어나거나 평지가 아닌 경우
		if (nc < 0 || map[nr][nc] != '.') {
			// 왼쪽으로 방향을 변경
			tank = new int[] { tank[0], tank[1], '<' };
		} else {
			// 왼쪽으로 이동
			tank = new int[] { nr, nc, '<' };
		}
	}

	public static void right() {
		int nr = tank[0];
		int nc = tank[1] + 1;
		// 범위를 벗어나거나 평지가 아닌 경우
		if (nc >= w || map[nr][nc] != '.') {
			// 오른쪽로 방향을 변경
			tank = new int[] { tank[0], tank[1], '>' };
		} else {
			// 오른쪽로 이동
			tank = new int[] { nr, nc, '>' };
		}
	}

	public static void shoot(int r, int c, int direction) {

		// 벽돌벽이면 평지로 변경
		if (map[r][c] == '*') {
			map[r][c] = '.';
			return;
		}

		// 강철벽이거나 경계를 벗어나면 종료
		int nr = r + dr[direction];
		int nc = c + dc[direction];
		if (map[r][c] == '#' || nr < 0 || nr >= h || nc < 0 || nc >= w)
			return;

		// 포탄 이동
		shoot(nr, nc, direction);
	}
}