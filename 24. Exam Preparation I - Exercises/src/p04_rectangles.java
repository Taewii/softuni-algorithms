import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p04_rectangles {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Rectangle> rectangles = new ArrayList<>();

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] params = input.split("[:\\s]+");
            String name = params[0];
            int x1 = Integer.parseInt(params[1]);
            int y1 = Integer.parseInt(params[2]);
            int x2 = Integer.parseInt(params[3]);
            int y2 = Integer.parseInt(params[4]);

            rectangles.add(new Rectangle(name, x1, y1, x2, y2));
        }

        for (Rectangle rectangle : rectangles) {
            if (rectangle.depth == 0) {
                calcDepth(rectangle, rectangles);
            }
        }

        StringBuilder sb = new StringBuilder();
        Rectangle max = Collections.max(rectangles);
        while (max != null) {
            sb.append(max.name).append(" < ");
            max = max.next;
        }

        System.out.println(sb.substring(0, sb.length() - 3));
    }

    private static void calcDepth(Rectangle rectangle, List<Rectangle> rectangles) {
        List<Rectangle> nested = new ArrayList<>();

        for (Rectangle current : rectangles) {
            if (!rectangle.equals(current) && current.isInside(rectangle)) {
                if (current.depth == 0) {
                    calcDepth(current, rectangles);
                }

                nested.add(current);
            }
        }

        if (nested.isEmpty()) {
            rectangle.depth = 1;
        } else {
            Rectangle max = Collections.max(nested);
            rectangle.depth = max.depth + 1;
            rectangle.next = max;
        }
    }

    static class Rectangle implements Comparable<Rectangle> {
        String name;
        int x1;
        int y1;
        int x2;
        int y2;
        int depth;
        Rectangle next;

        Rectangle(String name, int x1, int y1, int x2, int y2) {
            this.name = name;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        boolean isInside(Rectangle other) {
            return this.x1 >= other.x1 && this.y1 <= other.y1 && this.x2 <= other.x2 && this.y2 >= other.y2;
        }

        @Override
        public int compareTo(Rectangle o) {
            int result = this.depth - o.depth;
            if (result == 0) result = o.name.compareTo(this.name);
            return result;
        }
    }
}
