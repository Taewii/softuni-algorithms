import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03_towns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(reader.readLine().split(" ")[0]);
        }

        int[] incLen = new int[seq.length];
        int[] decLen = new int[seq.length];

        for (int i = 0; i < seq.length; i++) {
            incLen[i] = 1;

            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i] && incLen[j] + 1 > incLen[i]) {
                    incLen[i] = incLen[j] + 1;
                }
            }
        }

        for (int i = seq.length - 1; i >= 0; i--) {
            decLen[i] = 0;

            for (int j = seq.length - 1; j > i; j--) {
                if (seq[j] < seq[i] && decLen[j] + 1 > decLen[i]) {
                    decLen[i] = decLen[j] + 1;
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < seq.length; i++) {
            int temp = incLen[i] + decLen[i];
            if (temp > maxLen) {
                maxLen = temp;
            }
        }

        System.out.println(maxLen);
    }
}