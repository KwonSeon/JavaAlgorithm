import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        ArrayList<Integer> answer = new ArrayList<>();

        while (queue.size() > 1) {
            answer.add(queue.poll());
            queue.offer(queue.poll());
        }

        for (Integer integer : answer) {
            System.out.printf("%d ", integer);
        }
        for (Integer integer : queue){
            System.out.printf("%d ", integer);
        }
    }
}