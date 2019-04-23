import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p01_parking_zones {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int zoneCount = Integer.parseInt(reader.readLine());
        Zone[] zones = new Zone[zoneCount];

        for (int i = 0; i < zoneCount; i++) {
            String[] params = reader.readLine().split("[\\s,:]+");
            String name = params[0];
            int x = Integer.parseInt(params[1]);
            int y = Integer.parseInt(params[2]);
            int width = Integer.parseInt(params[3]);
            int height = Integer.parseInt(params[4]);
            double pricePerMin = Double.parseDouble(params[5]);

            zones[i] = new Zone(name, x, y, width, height, pricePerMin);
        }

        List<int[]> freeSpaces = new ArrayList<>();
        String[] params = reader.readLine().split("[\\s,;]+");
        for (int i = 0; i < params.length - 1; i += 2) {
            int[] arr = new int[2];
            arr[0] = Integer.parseInt(params[i]);
            arr[1] = Integer.parseInt(params[i + 1]);
            freeSpaces.add(arr);
        }

        params = reader.readLine().split(", ");
        int startX = Integer.parseInt(params[0]);
        int startY = Integer.parseInt(params[1]);
        int timePerStep = Integer.parseInt(reader.readLine());

        // was traversing all the spaces anyway, so it didn't matter if they were sorted by distance or not
        /*freeSpaces.sort((a, b) -> {
            int aDist = Math.abs(startX - a[0]) + Math.abs(startY - a[1]);
            int bDist = Math.abs(startX - b[0]) + Math.abs(startY - b[1]);

            return aDist - bDist;
        });*/

        String bestZone = null;
        int[] bestCoordinates = null;
        double bestPrice = Double.MAX_VALUE;
        int bestDist = Integer.MAX_VALUE;

        for (int[] space : freeSpaces) {
            for (Zone zone : zones) {
                if (zone.isSpaceInside(space)) {
                    int dist = Math.abs(startX - space[0]) + Math.abs(startY - space[1]) - 1;
                    double time = Math.ceil((dist * timePerStep * 2) / 60.0);
                    double price = time * zone.pricePerMin;

                    if (price < bestPrice || (price == bestPrice && dist < bestDist)) {
                        bestZone = zone.name;
                        bestCoordinates = space;
                        bestPrice = price;
                        bestDist = dist;
                    }
                }
            }
        }

        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f", bestZone, bestCoordinates[0], bestCoordinates[1], bestPrice);
    }

    static class Zone {
        String name;
        int x1;
        int x2;
        int y1;
        int y2;
        double pricePerMin;

        Zone(String name, int x, int y, int width, int height, double pricePerMin) {
            this.name = name;
            this.x1 = x;
            this.y1 = y;
            this.x2 = x + width;
            this.y2 = y + height;
            this.pricePerMin = pricePerMin;
        }

        boolean isSpaceInside(int[] space) {
            int x = space[0];
            int y = space[1];
            return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2;
        }
    }
}
