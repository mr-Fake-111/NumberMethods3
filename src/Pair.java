public class Pair {
    double a, b;

    public Pair() {
        a = 0;
        b = 0;
    }
    public Pair(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getDifference() {
        return b-a;
    }
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double[][] getVector() {
        double[][] result = {{a},{b}};
        return result;
    }
}
