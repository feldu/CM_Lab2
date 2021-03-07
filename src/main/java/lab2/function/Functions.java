package lab2.function;


import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

import static java.lang.Math.*;

@Getter
public enum Functions {
    f1("Многочлен 3й степени (из варианта)", x -> 2.335 * pow(x, 3) + 3.98 * pow(x, 2) - 4.52 * x - 3.11, "y = 2.335x^3 + 3.98x^2 − 4.52x − 3.11"),
    f2("Многочлен 3й степени (из лекции)", x -> pow(x, 3) - x + 4, "y = x^3 - x + 4"),
    f3("Функция с корнями", x -> pow(x, 1 / 3d) + pow(x, 1 / 6d) - 2, "y = x^(1/3) + x^(1/6) - 2"),
    f4("Периодическая функция 1", x -> sin(pow(x, 2) + x + 1), "y = sin(x^2 + x + 1)"),
    f5("Периодическая функция 2", x -> exp(pow(sin(x), 2)) - cos(x) - 1.488, "y = e^(sin^2(x)) - cos(x) - 1.488");
    private Function<Double, Double> function;
    private String textView;
    private String functionName;
    private double left;
    private double right;
    @Setter
    private double epsilon;

    Functions(String functionName, Function<Double, Double> function, String textView) {
        this.functionName = functionName;
        this.function = function;
        this.textView = textView;
    }

    public void setLimits(double a, double b) {
        if (a > b) {
            throw new RuntimeException("Левый граница больше правой.");
        }
        this.left = a;
        this.right = b;
    }

}
