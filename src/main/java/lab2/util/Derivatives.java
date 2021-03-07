package lab2.util;

import java.util.function.Function;

public class Derivatives {
    private static double dx = 0.0001;

    public static Function<Double, Double> derive(Function<Double, Double> function) {
        return (x) -> (function.apply(x + dx) - function.apply(x)) / dx;
    }
}
