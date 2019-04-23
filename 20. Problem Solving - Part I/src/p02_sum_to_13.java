import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p02_sum_to_13 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        System.out.println(sum(arr, 0, 0) ? "Yes" : "No");
    }

    private static boolean sum(int[] arr, int index, int currentSum) {
        if (index == arr.length) {
            return currentSum == 13;
        }

        return sum(arr, index + 1, currentSum + arr[index]) ||
                sum(arr, index + 1, currentSum - arr[index]);
    }
}
