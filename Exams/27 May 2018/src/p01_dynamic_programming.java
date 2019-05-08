import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class p01_dynamic_programming { // problem names are there to throw you off

    private static Map<Integer, BigDecimal> saved = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<Integer>[] people = new List[n];
        for (int i = 0; i < n; i++) {
            people[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            char[] params = reader.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (params[j] == 'R') {
                    people[i].add(j);
                }
            }
        }

        BigDecimal totalMoneyOwed = getTotalMoneyOwed(people);
        System.out.printf("$%.2f", totalMoneyOwed);
    }

    private static BigDecimal getTotalMoneyOwed(List<Integer>[] people) {
        BigDecimal total = BigDecimal.ZERO;

        for (int person = 0; person < people.length; person++) {
            total = total.add(getMoneyOwed(person, people));
        }

        return total;
    }

    private static BigDecimal getMoneyOwed(int person, List<Integer>[] people) {
        if (people[person].isEmpty()) {
            return BigDecimal.ONE;
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Integer referral : people[person]) {
            if (saved.containsKey(referral)) {
                sum = sum.add(saved.get(referral));
            } else {
                sum = sum.add(getMoneyOwed(referral, people)) ;
            }
        }

        BigDecimal total = BigDecimal.valueOf(people[person].size()).multiply(sum);
        saved.put(person, total);
        return total;
    }
}
