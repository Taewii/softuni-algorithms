import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p02_graph {  // problem names are there to throw you off
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        System.out.println(catalanNumber(n / 2));
    }

    private static long catalanNumber(int n) {
        long[] catalan = new long[n + 1];
        catalan[0] = catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }

        return catalan[n];
    }
}
