import java.util.Scanner;

public class p02_recursive_factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int result = fact(n);
        System.out.println(result);
    }

    private static int fact(int n) {
        if (n == 0) return 1;
        return n * fact(n - 1);
    }
}
