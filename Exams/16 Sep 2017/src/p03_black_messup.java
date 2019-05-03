import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p03_black_messup {

    private static Map<String, Atom> atoms = new LinkedHashMap<>();
    private static Map<String, List<String>> graph = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int atomCount = Integer.parseInt(reader.readLine());
        int connectionCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < atomCount; i++) {
            String[] atomArgs = reader.readLine().split("\\s+");
            Atom atom = new Atom(atomArgs[0], Integer.parseInt(atomArgs[1]), Integer.parseInt(atomArgs[2]));
            atoms.put(atom.name, atom);
            graph.put(atom.name, new ArrayList<>());
        }

        for (int i = 0; i < connectionCount; i++) {
            String[] connectionArgs = reader.readLine().split("\\s+");
            String from = connectionArgs[0];
            String to = connectionArgs[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        List<Set<Atom>> molecules = findConnectedComponents();
        int id = getBestMoleculeId(molecules);
        System.out.println(getMoleculeForce(molecules, id));
    }

    private static int getMoleculeForce(List<Set<Atom>> molecules, int id) {
        int score = 0;
        int maxExpiration = 1;
        List<Atom> taken = new ArrayList<>();
        for (Atom atom : molecules.get(id)) {
            if (atom.decay > maxExpiration) {
                taken.add(atom);
                maxExpiration = atom.decay;
                score += atom.mass;
            } else if (maxExpiration > taken.size()) {
                taken.add(atom);
                score += atom.mass;
            }
        }

        return score;
    }

    private static int getBestMoleculeId(List<Set<Atom>> molecules) {
        int bestId = 0;
        int bestScore = 0;

        for (int id = 0; id < molecules.size(); id++) {
            int score = getMoleculeForce(molecules, id);
            if (score >= bestScore) {
                bestScore = score;
                bestId = id;
            }
        }

        return bestId;
    }

    private static List<Set<Atom>> findConnectedComponents() {
        int id = 0;
        List<Set<Atom>> ids = new ArrayList<>();
        Set<String> marked = new TreeSet<>();

        for (String atom : graph.keySet()) {
            if (!marked.contains(atom)) {
                ids.add(new TreeSet<>());
                DFS(ids, marked, atom, id++);
            }
        }

        return ids;
    }

    private static void DFS(List<Set<Atom>> ids, Set<String> marked, String atom, int id) {
        marked.add(atom);
        ids.get(id).add(atoms.get(atom));
        for (String child : graph.get(atom)) {
            if (!marked.contains(child)) {
                DFS(ids, marked, child, id);
            }
        }
    }

    static class Atom implements Comparable<Atom> {
        String name;
        int mass;
        int decay;

        Atom(String name, int mass, int decay) {
            this.name = name;
            this.mass = mass;
            this.decay = decay;
        }

        @Override
        public int compareTo(Atom other) {
            return other.mass - this.mass;
        }
    }
}
