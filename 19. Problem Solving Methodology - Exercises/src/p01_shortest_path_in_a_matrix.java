import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p01_shortest_path_in_a_matrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        int[][] matrix = readMatrix(reader, rows, cols);
        int[][] graph = createGraphFromMatrix(matrix);
        List<Integer> nodePath = dijkstraAlgorithm(graph, 0, rows * cols - 1);

        List<Integer> path = Objects.requireNonNull(nodePath)
                .stream()
                .map(n -> {
                    int row = n / matrix[0].length;
                    int col = n % matrix[0].length;
                    return matrix[row][col];
                }).collect(Collectors.toList());

        int length = path.stream().mapToInt(i -> i).sum();
        System.out.println("Length: " + length);
        System.out.printf("Path: %s", path.toString().replaceAll("[\\[\\],]", ""));
    }

    private static int[][] createGraphFromMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] directions = {0, -1, -1, 0, 0, 1, 1, 0};
        var graph = new int[rows * cols][rows * cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int k = 0; k < directions.length; k += 2) {
                    int childRow = row + directions[k];
                    int childCol = col + directions[k + 1];

                    if (childRow >= 0 && childRow < rows && childCol >= 0 && childCol < cols) {
                        var parentNode = row * cols + col;
                        var childNode = childRow * cols + childCol;
                        graph[parentNode][childNode] = matrix[childRow][childCol];
                        graph[parentNode][parentNode] = 0;
                    }
                }
            }
        }

        return graph;
    }

    private static int[][] readMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            String[] args = reader.readLine().split("\\s+");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.parseInt(args[j]);
            }
        }

        return matrix;
    }

    private static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int n = graph.length;
        int[] distance = new int[n];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0;

        boolean[] used = new boolean[n];
        Integer[] previous = new Integer[n];

        while (true) {
            int minDistance = Integer.MAX_VALUE;
            int minNode = 0;
            for (int node = 0; node < n; node++) {
                if (!used[node] && distance[node] < minDistance) {
                    minDistance = distance[node];
                    minNode = node;
                }
            }

            if (minDistance == Integer.MAX_VALUE) {
                break;
            }
            used[minNode] = true;

            for (int i = 0; i < n; i++) {
                if (graph[minNode][i] != 0) {
                    int newDistance = distance[minNode] + graph[minNode][i];
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                        previous[i] = minNode;
                    }
                }
            }
        }

        if (distance[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }

        LinkedList<Integer> path = new LinkedList<>();
        Integer currentNode = destinationNode;
        while (currentNode != null) {
            path.addFirst(currentNode);
            currentNode = previous[currentNode];
        }

        return path;
    }
}
