import java.util.Scanner;
public class lab1_pp {
    public static int lucas(int n) //Створюємо клас для обрахунку числа Люка
    {

        // Base cases
        if (n == 0)
            return 2;
        if (n == 1)
            return 1;

        // recurrence relation
        return lucas(n - 1) +
                lucas(n - 2);
    }
    static boolean isTriangular(int num) //Створюємо клас для перевірки чи є число трикутним
    {
        if (num < 0)
            return false;

        // Трикутне число повинне бути сумою перщих n натуральних чисел
        int sum = 0;

        for (int n = 1; sum <= num; n++)
        {
            sum = sum + n;
            if (sum == num)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Будь ласка, введіть число: ");

        int n = scan.nextInt();
        int k = lucas(n);

        System.out.println("Число Люка: " + lucas(n));

        if (isTriangular(k))
            System.out.print("Число: " + lucas(n) + " " + "є трикутним!");
        else
            System.out.print("Число:" + lucas(n) + " "+ " НЕ трикутне!");

    }
}
