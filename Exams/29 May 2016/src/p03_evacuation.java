import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p03_evacuation {

    private static List<Integer>[] edges;
    private static List<Integer>[] distancesToEdge;
    private static int[] exits;
    private static int[] bestTimes;

    // 80/100 cuz the output is slow, cba changing it to a stringbuilder
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int roomCount = Integer.parseInt(reader.readLine());
        exits = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int connections = Integer.parseInt(reader.readLine());

        bestTimes = new int[roomCount];
        Arrays.fill(bestTimes, Integer.MAX_VALUE);

        edges = new ArrayList[roomCount];
        distancesToEdge = new ArrayList[roomCount];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
            distancesToEdge[i] = new ArrayList<>();
        }

        for (int i = 0; i < connections; i++) {
            String[] tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int time = toSeconds(tokens[2]);

            edges[from].add(to);
            edges[to].add(from);
            distancesToEdge[from].add(time);
            distancesToEdge[to].add(time);
        }

        int maxTime = toSeconds(reader.readLine());

        dijkstra();

        List<String> unsafe = new ArrayList<>();
        int last = 0;
        for (int i = 0; i < bestTimes.length; i++) {
            int time = bestTimes[i];
            if (time > bestTimes[last]) {
                last = i;
            }
            if (time > maxTime) {
                if (time < Integer.MAX_VALUE) {
                    unsafe.add(String.format("%d (%s)", i, fromSeconds(time)));
                } else {
                    unsafe.add(String.format("%d (unreachable)", i));
                }
            }
        }

        if (unsafe.isEmpty()) {
            System.out.println("Safe");
            System.out.printf("%d (%s)", last, fromSeconds(bestTimes[last]));
        } else {
            System.out.println("Unsafe");
            System.out.println(String.join(", ", unsafe));
        }
    }

    static void dijkstra() {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> bestTimes[i]));
        for (int room : exits) {
            bestTimes[room] = 0;
            queue.offer(room);
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < edges[vertex].size(); i++) {
                int child = edges[vertex].get(i);
                int timeToChild = distancesToEdge[vertex].get(i);
                int totalTime = bestTimes[vertex] + timeToChild;
                if (bestTimes[child] > totalTime) {
                    bestTimes[child] = totalTime;
                    queue.offer(child);
                }
            }
        }
    }

    static int toSeconds(String time) {
        String[] minutesAndSeconds = time.split(":");
        int minutes = Integer.parseInt(minutesAndSeconds[0]);
        int seconds = Integer.parseInt(minutesAndSeconds[1]);
        return minutes * 60 + seconds;
    }

    static String fromSeconds(int sec) {
        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int seconds = sec % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}