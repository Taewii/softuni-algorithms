import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p04_shop_keeper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> stock = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(HashSet::new));

        int[] items = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (!stock.contains(items[0])) {
            System.out.println("impossible");
            return;
        }

        int changes = 0;
        for (int i = 1; i < items.length; i++) {
            if (!stock.contains(items[i])) {
                Set<Integer> purchasesLeft = new LinkedHashSet<>();
                int lastPurchase = 0;
                for (int j = i + 1; j < items.length; j++) {
                    if (stock.contains(items[j])) {
                        if (purchasesLeft.add(items[j])) {
                            lastPurchase = items[j];
                        }
                    }
                }

                for (Integer product : stock) {
                    if (!purchasesLeft.contains(product)) {
                        lastPurchase = product;
                        break;
                    }
                }

                stock.remove(lastPurchase);
                stock.add(items[i]);
                changes++;
            }
        }

        System.out.println(changes);
    }
}
