package ex00;

public class Program {
    public static void main(String[] args) {
        Program.sumSixDigitsNumber();
    }

    public static void sumSixDigitsNumber() {
        int number = 479598;
        int sum = 0;

        while(number != 0) {
            sum += number % 10;
            number /= 10;
        }

        System.out.println(sum);
    }
}
