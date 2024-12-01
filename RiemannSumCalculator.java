import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Scanner;

public class RiemannSumCalculator {

    public static void main(String[] args) {
        new RiemannSumCalculator().runCalculator();
    }

    public void runCalculator() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the mathematical function in terms of x (e.g., 2*x^2 + 3*x + 1, sin(3x)^2): where x is the independent variable.");
        String function = scanner.nextLine();

        double a, b;
        while (true) {
            System.out.println("Enter the interval start (a): ");
            a = scanner.nextDouble();

            System.out.println("Enter the interval end (b): ");
            b = scanner.nextDouble();

            if (a < b) {
                break;
            } else {
                System.out.println("Invalid interval. Ensure that a < b.");
            }
        }

        int n;
        while (true) {
            System.out.println("Enter the number of subintervals (n): ");
            n = scanner.nextInt();

            if (n > 0) {
                break;
            } else {
                System.out.println("Invalid number of subintervals. Ensure that n > 0.");
            }
        }


        double leftRiemannSum = calculateLeftRiemannSum(function, a, b, n);
        double rightRiemannSum = calculateRightRiemannSum(function, a, b, n);
        double midpointRiemannSum = calculateMidpointRiemannSum(function, a, b, n);
        double trapezoidalSum = calculateTrapezoidalSum(function, a, b, n);

        System.out.printf("Left Riemann Sum: %.5f\n", leftRiemannSum);
        System.out.printf("Right Riemann Sum: %.5f\n", rightRiemannSum);
        System.out.printf("Midpoint Riemann Sum: %.5f\n", midpointRiemannSum);
        System.out.printf("Trapezoidal Sum: %.5f\n", trapezoidalSum);


        scanner.close();
    }

    public double evaluateFunction(String function, double x) {
        Expression e = new ExpressionBuilder(function)
                .variable("x")
                .build()
                .setVariable("x", x);
        return e.evaluate();
    }

    public double calculateLeftRiemannSum(String function, double a, double b, int n) {
        double width = (b - a) / n;
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            double x = a + i * width;
            sum += evaluateFunction(function, x) * width;
        }

        return sum;
    }

    public double calculateRightRiemannSum(String function, double a, double b, int n) {
        double width = (b - a) / n;
        double sum = 0.0;

        for (int i = 1; i <= n; i++) {
            double x = a + i * width;
            sum += evaluateFunction(function, x) * width;
        }

        return sum;
    }
    public double calculateMidpointRiemannSum(String function, double a, double b, int n) {
        double width = (b - a) / n;
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            double x = a + (i + 0.5) * width;
            sum += evaluateFunction(function, x) * width;
        }

        return sum;
    }
    public double calculateTrapezoidalSum(String function, double a, double b, int n) {
        double width = (b - a) / n;
        double sum = 0.0;

        for (int i = 0; i <= n; i++) {
            double x = a + i * width;
            if (i == 0 || i == n) {
                sum += evaluateFunction(function, x) / 2.0;
            } else {
                sum += evaluateFunction(function, x);
            }
        }

        return sum * width;
    }
}
