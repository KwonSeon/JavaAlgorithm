import java.io.*;
import java.util.*;

public class Main {

    static int n, m, minCnt;
    static char[][] board;
    static char[][] whiteFirst = new char[8][8];
    static char[][] blackFirst = new char[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 보드 입력 받기
        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 미리 흰색 시작, 검정색 시작 보드 구성
        initBoard();

        minCnt = Integer.MAX_VALUE;

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                minCnt = Math.min(minCnt, countMismatch(i, j, whiteFirst));
                minCnt = Math.min(minCnt, countMismatch(i, j, blackFirst));
            }
        }

        System.out.println(minCnt);
    }

    // 흰색 먼저, 검정색 먼저 보드를 미리 생성
    private static void initBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    whiteFirst[i][j] = 'W';
                    blackFirst[i][j] = 'B';
                } else {
                    whiteFirst[i][j] = 'B';
                    blackFirst[i][j] = 'W';
                }
            }
        }
    }

    // 보드 차이 계산
    public static int countMismatch(int r, int c, char[][] targetBoard) {
        int cnt = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[r + i][c + j] != targetBoard[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}