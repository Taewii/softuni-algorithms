import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class p04_best_lectures_schedule {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().substring("Lectures: ".length()));

        List<Lecture> lectures = new ArrayList<>();
        while (n-- > 0) {
            String[] params = reader.readLine().split("[\\s-:]+");
            String name = params[0];
            int start = Integer.parseInt(params[1]);
            int end = Integer.parseInt(params[2]);

            lectures.add(new Lecture(name, start, end));
        }

        List<Lecture> result = new ArrayList<>();

        lectures.sort(Comparator.comparingInt(a -> a.end));
        while (!lectures.isEmpty()) {
            Lecture current = lectures.remove(0);
            result.add(current);
            lectures.removeIf(l -> l.start < current.end);
        }

        System.out.printf("Lectures (%d):%n", result.size());
        result.forEach(System.out::println);
    }
}

class Lecture {
    private String name;
    int start;
    int end;

    Lecture(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%d-%d -> %s", this.start, this.end, this.name);
    }
}
