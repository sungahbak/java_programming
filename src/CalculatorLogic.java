//mathcalculation part

public class CalculatorLogic {
    // Factorial calculation (chap03)
    public double factorial(double n) {
        if (n < 0) return 0;
        double res = 1;
        for (int i = 1; i <= n; i++) res *= i;
        return res;
    }
    
 // four-principle arithmetic
    public double calculateBasic(double n1, String op, double n2) {
        switch(op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "×": return n1 * n2;
            case "÷": return (n2 != 0) ? n1 / n2 : 0;
            default: return n2;
        }
    }

    // Engineering Calculator Special Features (chap06)
    public double calculateSci(String op, double num) {
        switch(op) {
            case "sin": return Math.sin(Math.toRadians(num));
            case "cos": return Math.cos(Math.toRadians(num));
            case "tan": return Math.tan(Math.toRadians(num));
            case "√": return Math.sqrt(num);
            case "log": return Math.log10(num);
            case "π": return Math.PI;
            case "e": return Math.E;
            case "1/x": return 1 / num;
            case "n!": return factorial((int)num);
            default: return num;
        }
    }
}
