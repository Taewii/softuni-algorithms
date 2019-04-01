import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p01_fractional_knapsack_problem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine().substring("Capacity: ".length()));
        int n = Integer.parseInt(reader.readLine().substring("Items: ".length()));

        List<Item> items = new ArrayList<>();
        while (n-- > 0) {
            String[] params = reader.readLine().split(" -> ");
            double price = Double.parseDouble(params[0]);
            double weight = Double.parseDouble(params[1]);

            items.add(new Item(price, weight));
        }

        items.sort((a, b) -> Double.compare(b.price / b.weight, a.price / a.weight));

        double currentCapacity = 0;
        double totalPrice = 0;

        for (Item item : items) {
            double weightNeeded = capacity - currentCapacity;
            double percentage = weightNeeded >= item.weight ? 100 : (weightNeeded / item.weight) * 100;

            currentCapacity += weightNeeded >= item.weight ? item.weight : weightNeeded;
            totalPrice += (percentage * item.price) / 100;

            if (percentage == 100) {
                System.out.printf("Take 100%% of item with price %.2f and weight %.2f%n", item.price, item.weight);
            } else {
                System.out.printf("Take %.2f%% of item with price %.2f and weight %.2f%n", percentage, item.price, item.weight);
            }

            if (capacity == currentCapacity) {
                break;
            }
        }

        System.out.printf("Total price: %.2f", totalPrice);
    }
}

class Item {
    double price;
    double weight;

    Item(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }
}
