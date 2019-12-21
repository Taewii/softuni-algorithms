import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01_sowing {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());
        char[] arr = reader.readLine().replaceAll(" ", "").toCharArray();
        StringBuilder result = new StringBuilder();

        stuff(0, 0, n, arr, result);
        System.out.println(result.toString().trim());
    }

    static void stuff(int idx, int plants, int n, char[] arr, StringBuilder result) {
        if (plants == n) {
            result.append(arr).append(System.lineSeparator());
            return;
        }

        if (idx >= arr.length) {
            return;
        }

        int leftSide = Math.max(0, idx - 1);
        int rightSide = Math.min(arr.length - 1, idx + 1);

        if (arr[idx] != '0' && arr[idx] != '.' && arr[leftSide] != '.' && arr[rightSide] != '.') {
            char prev = arr[idx];
            arr[idx] = '.';
            stuff(idx + 1, plants + 1, n, arr, result);
            arr[idx] = prev;
        }

        stuff(idx + 1, plants, n, arr, result);
    }
}
