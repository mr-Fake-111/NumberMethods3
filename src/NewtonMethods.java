
public class NewtonMethods {

    static Pair system_solution = new Pair(0.582693214836437, 1.799929232862144);
    public static double newtonMethodForFunction(double a, double b, double accuracy, int funcIndex) throws Exception {
        if(a == b) {
            throw new Exception("Ты что творишь!? Как ты на точке корни искать собираешься?");
        }

        double x_old = (a+b)/2;
        double x_new = (a+b)/2;

        while(Math.abs(a-b) >= accuracy) {
            double x = (a+b)/2;
            if(Functions.derivative(x_old, funcIndex) != 0) {
                x = x_old - Functions.function(x_old, funcIndex) / Functions.derivative(x_old, funcIndex);
            }

            if(x >= a && x <= b) {
                x_old = x_new;
                x_new = x;
            } else {
                x_old = x_new;
                x_new = (a+b)/2;
            }

            double f_a = Functions.function(a, funcIndex);
            double f_b = Functions.function(b, funcIndex);
            double f_x = Functions.function(x_new, funcIndex);

            if(f_a == 0) return a;
            if(f_b == 0) return b;

            if(f_a*f_x > 0) {
                a = x_new;
            } else {
                b = x_new;
            }
        }
        return x_new;

    }

    public static Pair newtonMethodForSLAE(double x_old, double y_old, double accuracy, double lambda) throws Exception {
        double[][] dF = {{1, lambda*Math.cos(y_old-1)},{-1*Math.cos(x_old+1)*lambda, 1}};
        double[][] b = {{-1*lambda*(Math.sin(y_old-1)+x_old-1.3)},{-1*lambda*(y_old-Math.sin(x_old+1)-0.8)}};

        double[][] vector_old = {{x_old},{y_old}};
        double[][] vector_new = MathFunctions.sumMatrices(SLAEAccurateMethods.GaussMethod(dF, b), vector_old);

        while(MathFunctions.vectorNorm2(MathFunctions.subtractMatrices(vector_new, vector_old)) > accuracy) {
            vector_old = vector_new;

            dF[0][1] = lambda*Math.cos(vector_old[1][0]-1);
            dF[1][0] = -1*lambda*Math.cos(vector_old[0][0]+1);

            b[0][0] = -1*(lambda*Math.sin(vector_old[1][0]-1)+vector_old[0][0]-1.3);
            b[1][0] = -1*(vector_old[1][0]-lambda*Math.sin(vector_old[0][0]+1)-0.8);

            vector_new = MathFunctions.sumMatrices(vector_old, SLAEAccurateMethods.GaussMethod(dF, b));
        }

        return new Pair(vector_new[0][0], vector_new[1][0]);
    }

}
