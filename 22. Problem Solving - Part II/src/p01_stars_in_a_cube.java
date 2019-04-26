import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class p01_stars_in_a_cube {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        char[][][] cube = new char[n][n][n];
        Map<Character, Integer> stars = new TreeMap<>();
        int starCount = 0;

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" \\| ");
            for (int j = 0; j < n; j++) {
                cube[j][i] = tokens[j].replaceAll("\\s+", "").toCharArray();
            }
        }

        for (int w = 1; w < n - 1; w++) {
            for (int h = 1; h < n - 1; h++) {
                for (int d = 1; d < n - 1; d++) {
                    char letter = cube[w][h][d];

                    if (cube[w + 1][h][d] == letter &&
                        cube[w - 1][h][d] == letter &&
                        cube[w][h + 1][d] == letter &&
                        cube[w][h - 1][d] == letter &&
                        cube[w][h][d + 1] == letter &&
                        cube[w][h][d - 1] == letter) {

                        stars.putIfAbsent(letter, 0);
                        stars.put(letter, stars.get(letter) + 1);
                        starCount++;
                    }
                }
            }
        }

//        System.out.println(stars.values().stream().mapToInt(i -> i).sum());
//        stars.forEach((k, v) -> System.out.printf("%s -> %d%n", k, v));

        System.out.println(starCount);
        StringBuilder sb = new StringBuilder();
//        stars.forEach((k, v) -> sb.append(String.format("%s -> %d%n", k, v)));
        stars.forEach((k, v) -> sb.append(k).append(" -> ").append(v).append(System.lineSeparator()));
        System.out.println(sb.toString());
    }
}
