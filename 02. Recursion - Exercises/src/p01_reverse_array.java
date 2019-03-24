import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_reverse_array {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        printArrayInReverse(arr, arr.length - 1);
    }

    private static void printArrayInReverse(int[] arr, int index) {
        if (index >= 0) {
            System.out.print(arr[index] +  " ");
            printArrayInReverse(arr, index - 1);
        }
    }
}
