import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //решение одного нелинейного уравнения методом ньютона. Можно выбрать функцию из предложенных для нахождения корней и интервал поиска.
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Пожалуйста, введите номер вычисляемой функции и пределы поиска корней:");

            int funcIndex = scanner.nextInt(); //very important variable to calculate correct function
            Pair defPair = new Pair(scanner.nextDouble(), scanner.nextDouble());
            ArrayList<Pair> pairs = new ArrayList<>();
            pairs.add(defPair);

            while (pairs.get(0).getDifference() > Math.pow(10, -2)) {
                ArrayList<Pair> partPairs = new ArrayList<>();
                for (Pair pair : pairs) {
                    double a = pair.getA();
                    double b = pair.getB();
                    ;
                    for (double j = a; j < b; j += (b - a) / 10) {
                        if (Functions.function(j, funcIndex) * Functions.function(j + (b - a) / 10, funcIndex) <= 0) {
                            partPairs.add(new Pair(j, j + (b - a) / 10));
                        }
                    }
                }
                pairs.clear();
                pairs = partPairs;
                if (pairs.size() == 0) {
                    System.out.println("Корней не было найдено...");
                    break;
                }
            }
            if (pairs.size() != 0) {
                int iter = 1;
                HashSet<Double> results = new HashSet<Double>(); //порой корень может оказаться на границе множества, в связи с чем появиться в ответе дважды
                //в связи с этим я рещил использовать подобный костыль...
                for (Pair pair : pairs) {
                    try {
                        results.add(NewtonMethods.newtonMethodForFunction(pair.getA(), pair.getB(), Math.pow(10, -8), funcIndex));
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                for (Double result : results) {
                    System.out.println("Найденный корень " + iter + ": " + result);
                    iter++;
                }
            }
        }
        System.out.println();
        //Теперь решение системы через метод Ньютона, но уже без возможности кастомизации. Ровно по одной функции и на этом всё...
        {

            //1)Находим приближение графически: (x0, y0) = (0.5, 1.8);
            System.out.println("Теперь покажем найденные корни системы, соответствующей номеру 10 задания № 3:");
            System.out.println("согласно приближению графическим методом: ");
            Pair result = NewtonMethods.newtonMethodForSLAE(0.5, 1.8, Math.pow(10, -8), 1);
            System.out.println("(" + result.getA() + " , " + result.getB() + "), погрешность: "
                    + MathFunctions.vectorNorm2(MathFunctions.subtractMatrices(result.getVector(), NewtonMethods.system_solution.getVector())));

            //2)находим приближение по примечанию
            System.out.println("согласно приближению методом из примечания 1.4: ");
            Pair close_value = new Pair(1.3, 0.8);
            for (int i = 1; i <= 1000 ; i++) {
                close_value = NewtonMethods.newtonMethodForSLAE(close_value.getA(), close_value.getB(), Math.pow(10, -8), i/1000);
            }
            System.out.println("(" + close_value.getA() + " , " + close_value.getB() + "), погрешность: "
                    + MathFunctions.vectorNorm2(MathFunctions.subtractMatrices(result.getVector(), NewtonMethods.system_solution.getVector())));
        }
    }
}