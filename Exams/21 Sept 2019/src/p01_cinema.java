import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01_cinema {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] people = reader.readLine().split(", ");
        boolean[] isTaken = new boolean[people.length];

        String input;
        while (!"generate".equals(input = reader.readLine())) {
            String[] params = input.split(" - ");
            String name = params[0];
            int place = Integer.parseInt(params[1]) - 1;
            isTaken[place] = true;

            for (int i = 0; i < people.length; i++) {
                if (people[i].equals(name)) {
                    swap(people, i, place);
                }
            }
        }

        generatePermutations(0, people, isTaken);
    }

    private static void generatePermutations(int index, String[] arr, boolean[] t) {
        if (index >= arr.length) {
            System.out.println(String.join(" ", arr));
            return;
        }

        generatePermutations(index + 1, arr, t);

        for (int i = index + 1; i < arr.length; i++) {
            if (!t[i] && !t[index]) {
                swap(arr, index, i);
                generatePermutations(index + 1, arr, t);
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
