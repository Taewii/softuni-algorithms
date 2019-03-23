import java.util.Scanner;

public class p03_recursive_drawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        draw(n);
    }

    private static void draw(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(repeatStr("*", n));
        draw(n - 1);
        System.out.println(repeatStr("#", n));
    }

    private static String repeatStr(String str, int count) {
        String text = "";
        for (int i = 0; i < count; i++) {
            text += str;
        }
        return text;
    }
}
