import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class p04_island {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMaxArea(arr));
    }

    static int getMaxArea(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }

        int i = 0, max = 0, n = heights.length;
        Deque<Integer> s = new ArrayDeque<>();

        while (i < n) {
            while (!s.isEmpty() && heights[i] < heights[s.peek()]) {
                max = Math.max(max, heights[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));
            }
            s.push(i++);
        }

        while (!s.isEmpty()) {
            max = Math.max(max, heights[s.pop()] * (n - (s.isEmpty() ? 0 : s.peek() + 1)));
        }
        return max;
    }
}
