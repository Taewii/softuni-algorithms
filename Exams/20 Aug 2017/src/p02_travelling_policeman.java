import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p02_travelling_policeman {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int fuel = Integer.parseInt(reader.readLine());
        List<Street> streets = new ArrayList<>();

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] tokens = input.split(", ");
            String name = tokens[0];
            int carDamage = Integer.parseInt(tokens[1]);
            int pokemonCount = Integer.parseInt(tokens[2]);
            int length = Integer.parseInt(tokens[3]);
            int value = pokemonCount * 10 - carDamage;

            if (value >= 0) {
                streets.add(new Street(name, carDamage, pokemonCount, length, value));
            }
        }

        List<Street> path = fillKnapsack(streets, fuel);
        Collections.reverse(path);

        List<String> visited = new ArrayList<>();
        int pokemonCaught = 0;
        int carDamage = 0;

        for (Street street : path) {
            visited.add(street.name);
            pokemonCaught += street.pokemonCount;
            carDamage += street.damage;
            fuel -= street.length;
        }

        System.out.println(String.join(" -> ", visited));
        System.out.printf("Total pokemons caught -> %d%n", pokemonCaught);
        System.out.printf("Total car damage -> %d%n", carDamage);
        System.out.printf("Fuel Left -> %d", fuel);
    }

    private static List<Street> fillKnapsack(List<Street> streets, int fuel) {
        int[][] values = new int[streets.size() + 1][fuel + 1];
        boolean[][] isStreetTaken = new boolean[streets.size() + 1][fuel + 1];

        for (int i = 0; i < streets.size(); i++) {
            Street item = streets.get(i);
            int row = i + 1;

            for (int capacity = 1; capacity <= fuel; capacity++) {
                int valExcluded = values[row - 1][capacity];
                int valIncluded = 0;

                if (capacity >= item.length) {
                    valIncluded = item.value + values[row - 1][capacity - item.length];
                }

                if (valIncluded > valExcluded) {
                    values[row][capacity] = valIncluded;
                    isStreetTaken[row][capacity] = true;
                } else {
                    values[row][capacity] = valExcluded;
                }
            }
        }

//        int maxValue = values[streets.size()][fuel];

        List<Street> taken = new ArrayList<>();
        int currentFuel = fuel;

        for (int i = streets.size(); i > 0; i--) {
            if (isStreetTaken[i][currentFuel]) {
                Street item = streets.get(i - 1);
                taken.add(item);
                currentFuel -= item.length;
            }
        }

        return taken;
    }

    private static class Street {
        String name;
        int damage;
        int pokemonCount;
        int length;
        int value;

        Street(String name, int damage, int pokemonCount, int length, int value) {
            this.name = name;
            this.damage = damage;
            this.pokemonCount = pokemonCount;
            this.length = length;
            this.value = value;
        }
    }
}
