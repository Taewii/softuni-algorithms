import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p01_school_teams {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] girls = reader.readLine().split(", ");
        String[] boys = reader.readLine().split(", ");
        String[] girlsCombinations = new String[3];
        String[] boysCombinations = new String[2];

        List<String> girlsResult = new ArrayList<>();
        List<String> boysResult = new ArrayList<>();

        generateCombinations(0, 0, girlsCombinations, girls, girlsResult);
        generateCombinations(0, 0, boysCombinations, boys, boysResult);

        StringBuilder sb = new StringBuilder();
        for (String g : girlsResult) {
            for (String b : boysResult) {
                sb.append(String.format("%s, %s%n", g, b));
            }
        }

        System.out.println(sb.toString().trim());
    }

    private static void generateCombinations(int index, int border, String[] combinations, String[] arr, List<String> result) {
        if (index >= combinations.length) {
            result.add(String.join(", ", combinations));
            return;
        }

        for (int i = border; i < arr.length; i++) {
            combinations[index] = arr[i];
            generateCombinations(index + 1, i + 1, combinations, arr, result);
        }
    }
}
