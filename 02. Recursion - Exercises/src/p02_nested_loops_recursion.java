import java.util.Arrays;
import java.util.Scanner;

public class p02_nested_loops_recursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] result = new int[n];

        loop(result, 0);
    }

    private static void loop(int[] result, int index) {
        if (index == result.length) {
            System.out.println(Arrays.toString(result).replaceAll("[\\[\\],]", ""));
            return;
        }

        for (int i = 1; i <= result.length; i++) {
            result[index] = i;
            loop(result, index + 1);
        }
    }
}
