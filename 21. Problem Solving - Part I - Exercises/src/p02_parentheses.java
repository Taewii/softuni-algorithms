import java.util.Scanner;

public class p02_parentheses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        char[] str = new char[2 * n];
        StringBuilder sb = new StringBuilder();
        generateParentheses(str, 0, n, 0, 0, sb);
        System.out.print(sb.reverse().toString().trim());
    }

    private static void generateParentheses(char[] str, int pos, int n, int open, int close, StringBuilder sb) {
        if (close == n) {
            for (int i = str.length - 1; i >= 0; i--) {
                sb.append(str[i]);
            }
            // appends 2 lines to the judge system for some reason
            // sb.append(System.lineSeparator());
            sb.append("\n");
        } else {
            if (open > close) {
                str[pos] = ')';
                generateParentheses(str, pos + 1, n, open, close + 1, sb);
            }
            if (open < n) {
                str[pos] = '(';
                generateParentheses(str, pos + 1, n, open + 1, close, sb);
            }
        }
    }
}
