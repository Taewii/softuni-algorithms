import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_recursive_array_sum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int result = sumArr(arr, 0);
        System.out.println(result);
    }

    private static int sumArr(int[] arr, int index) {
        if (index == arr.length) return 0;
        return arr[index] + sumArr(arr, index + 1);
    }
}
