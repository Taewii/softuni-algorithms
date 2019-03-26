import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class p05_words {

    private static char[] chars;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        chars = reader.readLine().toCharArray();

        if (areSymbolsInWordUnique(chars)) {
            count = getFactorial(chars.length);
        } else {
            calculatePermutations(0);
        }

        System.out.println(count);
    }

    private static void calculatePermutations(int index) {
        if (index == chars.length) {
            boolean isFound = true;
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] == chars[i + 1]) {
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                count++;
            }
        }

        Set<Character> swapped = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (!swapped.contains(chars[i])) {
                char temp = chars[index];
                chars[index] = chars[i];
                chars[i] = temp;

                calculatePermutations(index + 1);

                chars[i] = chars[index];
                chars[index] = temp;

                swapped.add(chars[i]);
            }
        }
    }

    private static int getFactorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    private static boolean areSymbolsInWordUnique(char[] chars) {
        Set<Character> unique = new HashSet<>();
        for (char ch : chars) {
            unique.add(ch);
        }

        return unique.size() == chars.length;
    }
}
