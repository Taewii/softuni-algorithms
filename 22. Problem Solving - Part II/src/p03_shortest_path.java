import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class p03_shortest_path {

    private static int count;
    private static List<char[]> directions;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String path = reader.readLine();
        char[] seq = new char[]{'L', 'R', 'S'};
        int k = getK(path.toCharArray());
        char[] variations = new char[k];
        directions = new LinkedList<>();

        gen(seq, variations, k, 0);
        System.out.println(count);

        StringBuilder sb = new StringBuilder();
        for (char[] direction : directions) {
            String temp = path;

            for (char ch : direction) {
                temp = temp.replaceFirst("\\*", String.valueOf(ch));
            }

            sb.append(temp).append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }

    private static int getK(char[] path) {
        int k = 0;
        for (char c : path) if (c == '*') k++;
        return k;
    }

    private static void gen(char[] seq, char[] variations, int k, int index) {
        if (index >= k) {
            directions.add(Arrays.copyOf(variations, k));
            count++;
        } else {
            for (char element : seq) {
                variations[index] = element;
                gen(seq, variations, k, index + 1);
            }
        }
    }
}
