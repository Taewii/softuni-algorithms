import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p03_binary_search {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());

        int index = binarySearch(arr, n, 0, arr.length - 1);
        System.out.printf("Index of %d is %d", n, index);
    }

    private static int binarySearch(int[] arr, int item, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;

        if (arr[mid] == item) {
            return mid;
        }

        if (arr[mid] < item) {
            return binarySearch(arr, item, mid, hi);
        }

        return binarySearch(arr, item, 0, mid);
    }
}
