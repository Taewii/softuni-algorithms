import java.util.Arrays;
import java.util.Scanner;

public class p05_egyptian_fractions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] params = Arrays.stream(scanner.nextLine().split("/")).mapToInt(Integer::parseInt).toArray();
        int nom = params[0];
        int denominator = params[1];

        if (denominator < 0 || nom > denominator) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }

        System.out.printf("%d/%d = ", nom, denominator);

        while (denominator % nom != 0) {
            int divider = (nom + denominator) / nom;
            System.out.printf("1/%d + ", divider);

            nom = (nom * divider) - denominator;
            denominator = denominator * divider;
        }

        System.out.printf("1/%d", (denominator / nom));
    }
}
