import java.util.Scanner;

public class p01_fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] memo = new int[n + 1];
        int result = getFibWithMemorization(memo, n);
//        int result = getFibIterative(n);

        System.out.println(result);
    }

    private static int getFibIterative(int n) {
        int previous = 1;
        int current = 1;

        for (int i = 3; i <= n; i++) {
            int old = current;
            current += previous;
            previous = old;
        }

        return current;
    }

    private static int getFibWithMemorization(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        int result = getFibWithMemorization(memo, n - 1) + getFibWithMemorization(memo, n - 2);
        memo[n] = result;

        return result;
    }
}
