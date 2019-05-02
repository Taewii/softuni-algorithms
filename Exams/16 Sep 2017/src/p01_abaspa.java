import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01_abaspa {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] first = reader.readLine().toCharArray();
        String second = reader.readLine();

        String longest = "";

        for (int i = 0; i < first.length; i++) {
            StringBuilder current = new StringBuilder(String.valueOf(first[i]));
            for (int j = i + 1; j < first.length; j++) {
                current.append(first[j]);

                if (!second.contains(current)) {
                    break;
                }

                if (longest.length() < current.length()) {
                    longest = current.toString();
                }
            }
        }

        System.out.println(longest);
    }
}
