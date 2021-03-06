import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p02_secCover {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] universe = Arrays.stream(reader.readLine().substring(10).split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberOfSets = Integer.parseInt(reader.readLine().substring(16).trim());

        List<int[]> sets = new ArrayList<>();
        while (numberOfSets-- > 0) {
            sets.add(Arrays.stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray());
        }

        List<int[]> chosenSets = chooseSets(sets, universe);

        System.out.printf("Sets to take (%d):%n", chosenSets.size());

        chosenSets.forEach(s -> System.out
                .println(Arrays.toString(s)
                        .replaceAll("\\[", "{ ")
                        .replaceAll("]", " }")));

    }

    static List<int[]> chooseSets(final List<int[]> sets, final int[] universe) {
        List<int[]> chosenSets = new ArrayList<>();

        Set<Integer> universeSet = Arrays.stream(universe)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));

        Set<Integer> usedSets = new HashSet<>();

        while (!universeSet.isEmpty()) {
            int bestIndex = -1;
            int bestMatches = 0;

            for (int index = 0; index < sets.size(); index++) {
                if (usedSets.contains(index)) {
                    continue;
                }

                int currentMatches = 0;
                for (int number : sets.get(index)) {
                    if (universeSet.contains(number)) {
                        currentMatches++;
                    }
                }

                if (currentMatches > bestMatches) {
                    bestMatches = currentMatches;
                    bestIndex = index;
                }
            }

            if (bestIndex >= 0) {
                usedSets.add(bestIndex);

                int[] set = sets.get(bestIndex);

                chosenSets.add(set);

                for (int number : set) {
                    universeSet.remove(number);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        return chosenSets;
    }
}
