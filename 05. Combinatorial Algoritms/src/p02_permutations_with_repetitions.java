import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class p02_permutations_with_repetitions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\s+");
        
        generatePermutations(input, 0);
    }

    private static void generatePermutations(String[] arr, int index) {
        if (index >= arr.length) {
            System.out.println(String.join(" ", arr));
            return;
        }

        generatePermutations(arr, index + 1);

        Set<String> used = new HashSet<>();
        used.add(arr[index]);
        for (int i = index + 1; i < arr.length; i++) {
            if (!used.contains(arr[i])) {
                used.add(arr[i]);
                swap(arr, index, i);
                generatePermutations(arr, index + 1);
                swap(arr, index, i);
            }

        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
