import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p01_medenka {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String medenka = reader.readLine().replaceAll("\\s+", "");

        int nuts = 0;
        for (int i = 0; i < medenka.length(); i++) {
            if (medenka.charAt(i) == '1') nuts++;
        }

        List<Integer> result = new ArrayList<>();
        int start = medenka.indexOf("1");
        generateCuts(start, 0, nuts, medenka, result);
    }

    private static void generateCuts(int start, int cuts, int nuts, String medenka, List<Integer> indices) {
        if (cuts == nuts - 1) {
            print(medenka, indices);
        } else {
            int end = medenka.indexOf("1", start + 1);

            for (int i = start; i < end; i++) {
                indices.add(i);
                generateCuts(end, cuts + 1, nuts, medenka, indices);
                indices.remove(indices.size() - 1);
            }
        }
    }

    private static void print(String medenka, List<Integer> indices) {
        StringBuilder sb = new StringBuilder();
        char[] chars = medenka.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (indices.contains(i)) sb.append("|");
        }

        System.out.println(sb.toString());
    }
}
