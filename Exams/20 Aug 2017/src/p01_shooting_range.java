import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p01_shooting_range {

    private static int[] arr;
    private static boolean[] marked;
    private static int sumNeeded;
    private static List<String> output = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        marked = new boolean[arr.length];
        sumNeeded = Integer.parseInt(reader.readLine());

        generatePermutations(0);

        System.out.println(String.join("\n", output));
    }

    private static void generatePermutations(int index) {
        int currentSum = getCurrentSum();
        if (sumNeeded == currentSum) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (marked[i]) {
                    sb.append(arr[i]).append(" ");
                }
            }
            output.add(sb.toString().trim());
        }

        if (index >= arr.length || currentSum > sumNeeded) {
            return;
        }

        Set<Integer> swapped = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (!swapped.contains(arr[i])) {
                swap(index, i);
                marked[index] = true;

                generatePermutations(index + 1);

                swap(index, i);
                marked[index] = false;

                swapped.add(arr[i]);
            }
        }
    }

    private static int getCurrentSum() {
        int sum = 0;
        int multiplier = 1;
        for (int i = 0; i < arr.length; i++) {
            if (marked[i]) {
                sum += arr[i] * multiplier++;
            }
        }

        return sum;
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
