import java.util.Scanner;

public class p07_binomial_coefficients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        System.out.println(binomial(n, k));
    }

    private static long binomial(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }

        long b = 1;
        for (int i = 1, m = n; i <= k; i++, m--) {
            b = b * m / i;
        }

        return b;
    }

    private static long binomialSlow(int n, int k) {
        if ((n == k) || (k == 0)) {
            return 1;
        }

        return binomialSlow(n - 1, k) + binomialSlow(n - 1, k - 1);
    }
}
