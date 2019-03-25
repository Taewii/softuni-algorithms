import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p01_merge_sort {

    private static int[] aux;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        aux = new int[arr.length];

        mergeSort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));
    }

    private static void mergeSort(int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);

        if (arr[mid] < arr[mid + 1]) {
            return;
        }

        System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid + 1;
        for (int index = lo; index <= hi; index++) {
            if (i > mid) {
                arr[index] = aux[j++];
            } else if (j > hi) {
                arr[index] = aux[i++];
            } else if (aux[i] < aux[j]) {
                arr[index] = aux[i++];
            } else {
                arr[index] = aux[j++];
            }
        }
    }
}
