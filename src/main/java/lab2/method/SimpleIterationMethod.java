package lab2.method;

import lab2.function.Functions;
import lab2.plot.Plot;
import lab2.plot.Series;
import lab2.util.Derivatives;
import lab2.util.IntervalChecker;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class SimpleIterationMethod implements SolvingMethod {
    private IntervalChecker intervalChecker = new IntervalChecker();

    @Override
    public double solve(Functions function) {
        double a = function.getLeft();
        double b = function.getRight();
        double x, lastX;
        double fx;
        Function<Double, Double> deriveFunc = Derivatives.derive(function.getFunction());
        log.info("f'(a)={}, f'(b)={}", deriveFunc.apply(a), deriveFunc.apply(b));
        double lambda = -1 / Math.max(deriveFunc.apply(a), deriveFunc.apply(b)); //mb abs???
        log.info("Lambda={}", lambda);
        Function<Double, Double> fi_minus_x = function.getFunction().andThen((df) -> df * lambda);
        drawFi(fi_minus_x, a, b);
        lastX = deriveFunc.apply(a) > deriveFunc.apply(b) ? a : b;
        int i = 0;
        while (true) {
            x = fi(fi_minus_x, lastX);
            fx = function.getFunction().apply(x);
            log.info("step {}: x(i)={}, x(i+1)={}, fi(x(i+1))={}, f(x(i+1))={}, |x(i+1)-x(i)|={}", i, lastX, x, fi(fi_minus_x, x), fx, Math.abs(x - lastX));
            if (Math.abs(x - lastX) <= function.getEpsilon()) break;
            lastX = x;
            i++;
        }
        return x;
    }

    @Override
    public void checkConditions(Functions function) {
        double a = function.getLeft();
        double b = function.getRight();
        Function<Double, Double> deriveFunc = Derivatives.derive(function.getFunction());
        log.info("f'(a)={}, f'(b)={}", deriveFunc.apply(a), deriveFunc.apply(b));
        double lambda = -1 / Math.max(deriveFunc.apply(a), deriveFunc.apply(b)); //mb abs???
        Function<Double, Double> dfi_dx = deriveFunc.andThen((df) -> df * lambda).andThen(value -> value + 1);
        if (!intervalChecker.functionLessThanOne(dfi_dx, a, b)) {
            log.warn("Fi'(x) has interval with values greater than 1");
        }
        drawPlot(function);
        new Plot("Ф'(x)", new Series("Ф'(x)", dfi_dx, a, b)).save("./dFi_dx");
    }


    private void drawPlot(Functions function) {
        Series series = new Series(function.getTextView(), function.getFunction(), function.getLeft(), function.getRight());
        Plot plot = new Plot(function.getFunctionName(), series);
        plot.save("./plot");
    }

    private void drawFi(Function<Double, Double> fi_minus_x, double a, double b) {
        Series fi = new Series("Ф(x)");
        Series x = new Series("y=x", value -> value, a, b);
        double step = (b - a) / 20;
        //make plot with padding step*5
        for (double i = a - step * 5; i <= b + step * 5; i += step) {
            fi.getXData().add(i);
            fi.getYData().add(fi(fi_minus_x, i));
        }
        Plot plot = new Plot("Метод простой итерации", fi, x);
        plot.save("./Fi");
    }

    //Костыль
    private double fi(Function<Double, Double> fi_minus_x, double x) {
        return x + fi_minus_x.apply(x);
    }

}
