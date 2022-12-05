public class Functions {
    public static double function(double x, int funcIndex) {
        switch (funcIndex) {
            case 10:
                return Math.pow(x, 2) + 4 * Math.sin(x)-2;
            default:
                return x;

        }
    }
    public static double derivative(double x, int funcIndex) {
        switch (funcIndex) {
            case 10:
                return 2 * x + 4 * Math.cos(x);
            default:
                return 1;

        }
    }
}
