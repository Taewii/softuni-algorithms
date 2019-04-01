import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p02_processor_scheduling {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().substring("Tasks: ".length()));

        int largestDeadline = 0;
        List<Task> tasks = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String[] params = reader.readLine().split(" - ");
            int value = Integer.parseInt(params[0]);
            int deadline = Integer.parseInt(params[1]);

            if (deadline > largestDeadline) {
                largestDeadline = deadline;
            }

            tasks.add(new Task(i, value, deadline));
        }

        tasks.sort((a, b) -> {
            int cmp = b.value - a.value;
            if (cmp == 0) cmp = a.deadline - b.deadline;
            return cmp;
        });

        List<Task> result = new ArrayList<>();
        int totalValue = 0;

        for (int i = 0; i < largestDeadline; i++) {
            Task task = tasks.get(i);
            totalValue += task.value;
            result.add(task);
        }

        StringBuilder sb = new StringBuilder();
        result.stream().sorted((a, b) -> {
            int cmp = a.deadline - b.deadline;
            if (cmp == 0) cmp = b.value - a.value;
            return cmp;
        })
                .limit(largestDeadline)
                .forEach(t -> sb.append(t.position).append(" -> "));

        System.out.printf("Optimal schedule: %s%n", sb.substring(0, sb.length() - 4));
        System.out.printf("Total value: %d", totalValue);
    }
}

class Task {
    int position;
    int value;
    int deadline;

    Task(int position, int value, int deadline) {
        this.position = position;
        this.value = value;
        this.deadline = deadline;
    }
}
