import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, matrix[][], minScore[], maxScore[];
	static int dk[] = {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		matrix = new int[n][3];
		minScore = new int[3];
		maxScore = new int[3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (i==0){
					minScore[j] = matrix[i][j];
					maxScore[j] = matrix[i][j];
				}
			}
		}

		for (int i = 1; i < n; i++) {
			int[] newMinScore = new int[3];
			int[] newMaxScore = new int[3];

			for (int j = 0; j < 3; j++) {
				int max = Integer.MIN_VALUE;
				int min = Integer.MAX_VALUE;

				for (int k = 0; k < 3; k++) {
					int nk = j + dk[k];
					if (nk < 0 || nk >= 3) continue;
					max = Math.max(max, maxScore[nk] + matrix[i][j]);
					min = Math.min(min, minScore[nk] + matrix[i][j]);
				}

				newMaxScore[j] = max;
				newMinScore[j] = min;
			}

			maxScore = newMaxScore;
			minScore = newMinScore;
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++) {
			max = Math.max(max, maxScore[i]);
			min = Math.min(min, minScore[i]);
		}

		sb.append(max).append(" ").append(min);

		System.out.println(sb.toString());

	}
}