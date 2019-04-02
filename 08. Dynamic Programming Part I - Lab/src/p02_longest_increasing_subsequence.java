import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p02_longest_increasing_subsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] lis = new int[numbers.length];
        int[] prev = new int[numbers.length];

        for (int currentIndex = 0; currentIndex < numbers.length; currentIndex++) {
            lis[currentIndex] = 1;
            prev[currentIndex] = -1;

            for (int prevIndex = 0; prevIndex < currentIndex; prevIndex++) {
                if (numbers[prevIndex] < numbers[currentIndex] && lis[prevIndex] >= lis[currentIndex]) {
                    lis[currentIndex] = lis[prevIndex] + 1;
                    prev[currentIndex] = prevIndex;
                }
            }
        }

        int lastIndex = -1;
        int maxLen = 0;

        for (int index = 0; index < numbers.length; index++) {
            if (maxLen < lis[index]) {
                maxLen = lis[index];
                lastIndex = index;
            }
        }

        int[] lisElements = new int[maxLen];

        while (lastIndex != -1) {
            lisElements[--maxLen] = numbers[lastIndex];
            lastIndex = prev[lastIndex];
        }

        System.out.println(Arrays.toString(lisElements).replaceAll("[\\[\\],]", ""));
    }
}
