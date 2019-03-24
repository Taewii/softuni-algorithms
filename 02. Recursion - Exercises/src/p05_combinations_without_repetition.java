import java.util.Arrays;
import java.util.Scanner;

public class p05_combinations_without_repetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[k];

        loop(arr, 1, n, 0);
    }

    private static void loop(int[] arr, int start, int n, int index) {
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[index] = i;
            loop(arr, i + 1, n, index + 1);
        }
    }
}
