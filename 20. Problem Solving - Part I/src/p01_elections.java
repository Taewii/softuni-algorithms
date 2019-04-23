import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class p01_elections {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int k = in.nextInt();
        int n = in.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        BigInteger[] sums = new BigInteger[Arrays.stream(nums).sum() + 1];
        Arrays.fill(sums, BigInteger.ZERO);
        sums[0] = BigInteger.ONE;

        for (int num : nums) {
            for (int i = sums.length - 1; i >= 0; i--) {
                if (!sums[i].equals(BigInteger.ZERO)) {
                    sums[i + num] = sums[i + num].add(sums[i]);
                }
            }
        }

        BigInteger total = BigInteger.ZERO;
        for (int i = k; i < sums.length; i++) {
            total = total.add(sums[i]);
        }

        System.out.println(total);
    }
}
