import java.util.Scanner;

public class p01_sequences_of_limited_sum {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] variations = new int[n];
        gen(n, 0, 0, variations);

        System.out.println(sb.toString());
    }

    private static void gen(int maxSum, int currentSum, int index, int[] variations) {
        if (currentSum <= maxSum && currentSum != 0) {
            for (int variation : variations) {
                if (variation == 0) break;
                sb.append(variation).append(" ");
            }
           sb.append(System.lineSeparator());
        }

        if (currentSum >= maxSum) {
            return;
        }

        for (int i = 1; i <= maxSum; i++) {
            variations[index] = i;
            gen(maxSum, currentSum + i, index + 1, variations);
        }
        variations[index] = 0;
    }
}
