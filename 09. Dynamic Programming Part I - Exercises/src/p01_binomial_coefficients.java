import java.util.Scanner;

public class p01_binomial_coefficients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        System.out.println(binomialIterative(n, k));
    }

    private static long binomialIterative(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }

        long b = 1;
        for (int i = 1, m = n; i <= k; i++, m--) {
            b = b * m / i;
        }

        return b;
    }
}
