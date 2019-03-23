import java.util.Arrays;
import java.util.Scanner;

public class p04_generating_01_vectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        printVectors(arr, 0);
    }

    private static void printVectors(int[] arr, int index) {
        if (index >= arr.length) {
            System.out.println(Arrays.toString(arr).replaceAll("[\\[\\], ]", ""));
            return;
        }

        arr[index] = 0;
        printVectors(arr, index + 1);
        arr[index] = 1;
        printVectors(arr, index + 1);
    }
}
