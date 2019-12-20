import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class p02_word_cruncher {

    static Deque<String> queue = new ArrayDeque<>();
    static Set<String> result = new HashSet<>();
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] words = reader.readLine().split(", ");
        String str = reader.readLine();
        used = new boolean[words.length];

        compose(words, str);
        System.out.println(String.join(System.lineSeparator(), result).trim());
    }

    static void compose(String[] words, String str) {
        if (str.equals("")) {
            result.add(String.join(" ", queue));
            return;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int endIndex = Math.min(str.length(), word.length());
            String start = str.substring(0, endIndex);
            String rest = str.substring(endIndex);

            if (start.equals(word) && !used[i]) {
                queue.offer(word);
                used[i] = true;
                compose(words, rest);
                used[i] = false;
                queue.pollLast();
            }
        }
    }
}
