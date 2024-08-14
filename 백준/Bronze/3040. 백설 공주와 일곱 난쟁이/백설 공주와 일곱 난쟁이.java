import java.io.*;
import java.util.*;

/**
 * 
 * 1. 입력 받으면서 총합을 구함 2. 9명 중 7명의 합이 100이므로 남은 2명의 합은 총합-100 3. 조합으로 값을 구함 4. 남은
 * 배열을 제외하고 출력
 *
 */

public class Main {
	static int[] fakeDwarf = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 1. 총합과 목표값 구하기
		int[] dwarfs = new int[9];
		int amount = 0;
		for (int i = 0; i < 9; i++) {
			int dwarf = Integer.parseInt(br.readLine());
			dwarfs[i] = dwarf;
			amount += dwarf;
		}
		int target = amount - 100;
		int[] temp = new int[2];

		// 2. 가짜 난쟁이 찾기
		findFakeDwarf(dwarfs, temp, target, 0, 0);

		// 3. 가짜 난쟁이 제외한 배열 만들기
		for (int dwarf : dwarfs) {
			if (dwarf == fakeDwarf[0] || dwarf == fakeDwarf[1])
				continue;
			sb.append(dwarf).append('\n');
		}

		System.out.println(sb.toString());

	}

	/**
	 * 가짜 난쟁이 찾기
	 * 
	 * @param dwarfs 난쟁이 배열
	 * @param temp   가짜 난쟁이 저장
	 * @param target 목표 키의 합
	 * @param index  depth, 찾은 수
	 * @param start  시작 지점
	 */
	public static void findFakeDwarf(int[] dwarfs, int[] temp, int target, int index, int start) {

		if (index == 2) {
			int amount = 0;
			for (int dwarf : temp) {
				amount += dwarf;
			}
			if (amount == target) {
				fakeDwarf[0] = temp[0];
				fakeDwarf[1] = temp[1];
			}

			return;
		}

		for (int i = start; i < 9; i++) {
			temp[index] = dwarfs[i];
			findFakeDwarf(dwarfs, temp, target, index + 1, i + 1);
		}
	}
}