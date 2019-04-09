import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p04_salaries {

    private static List<Integer>[] employees;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        employees = new List[n];
        salaries = new long[n];
        int[] managersCount = new int[n];

        for (int i = 0; i < n; i++) {
            char[] line = reader.readLine().toCharArray();
            List<Integer> emp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (line[j] == 'Y') {
                    emp.add(j);
                    managersCount[j]++;
                }
            }
            employees[i] = emp;
        }

        for (int i = 0; i < managersCount.length; i++) {
            if (managersCount[i] == 0) {
                calcSalary(i);
            }
        }

        System.out.println(Arrays.stream(salaries).sum());
    }

    private static long calcSalary(int employee) {
        long sum = 0;

        if (salaries[employee] != 0) {
            return salaries[employee];
        }

        if (employees[employee].isEmpty()) {
            salaries[employee] = 1;
            return 1;
        }

        for (Integer emp : employees[employee]) {
            sum += calcSalary(emp);
        }

        salaries[employee] = sum;
        return sum;
    }
}
