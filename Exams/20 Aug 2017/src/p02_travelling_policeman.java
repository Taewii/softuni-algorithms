import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class p02_travelling_policeman { // TODO: 4.5.2019 г. this isn't greedy, its a knapsack
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int fuel = Integer.parseInt(reader.readLine());
        Set<Street> streets = new TreeSet<>((a, b) -> b.value - a.value);

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] tokens = input.split(", ");
            String name = tokens[0];
            short carDamage = Short.parseShort(tokens[1]);
            byte pokemonCount = Byte.parseByte(tokens[2]);
            short length = Short.parseShort(tokens[3]);
            int value = ((pokemonCount * 10) - carDamage) - length;

            if (value > 0) {
                streets.add(new Street(name, carDamage, pokemonCount, length, value));
            }
        }

        List<String> visited = new ArrayList<>();
        int pokemonsCought = 0;
        int carDamage = 0;
        for (Street street : streets) {
            if (fuel >= street.length) {
                visited.add(street.name);
                pokemonsCought += street.pokemonCount;
                carDamage += street.damage;
                fuel -= street.length;
            }
        }

        System.out.println(String.join(" -> ", visited));
        System.out.printf("Total pokemons caught -> %d%n", pokemonsCought);
        System.out.printf("Total car damage -> %d%n", carDamage);
        System.out.printf("Fuel Left -> %d", fuel);
    }

    static class Street {
        String name;
        short damage;
        byte pokemonCount;
        short length;
        int value;

        Street(String name, short damage, byte pokemonCount, short length, int value) {
            this.name = name;
            this.damage = damage;
            this.pokemonCount = pokemonCount;
            this.length = length;
            this.value = value;
        }
    }
}
