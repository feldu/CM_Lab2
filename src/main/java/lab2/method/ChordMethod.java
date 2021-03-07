package lab2.method;

import lab2.function.Functions;
import lab2.plot.Plot;
import lab2.plot.Series;
import lab2.util.IntervalChecker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChordMethod implements SolvingMethod {
    private IntervalChecker intervalChecker = new IntervalChecker();

    @Override
    public double solve(Functions function) {
        double a = function.getLeft();
        double b = function.getRight();
        double x, fa, fb, fx, lastX = Double.MAX_VALUE;
        int i = 0;
        while (true) {
            fa = function.getFunction().apply(a);
            fb = function.getFunction().apply(b);
            x = (a * fb - b * fa) / (fb - fa);
            fx = function.getFunction().apply(x);
            log.info("step {}: a={}, b={}, x={}, f(a)={}, f(b)={}, f(x)={}, |x(i+1)-x(i)|={}", i, a, b, x, fa, fb, fx, lastX == Double.MAX_VALUE ? "---" : Math.abs(x - lastX));
            if (fx * fa < 0) b = x;
            else if (fx * fb < 0) a = x;
            if (Math.abs(x - lastX) <= function.getEpsilon()) break;
            lastX = x;
            i++;
        }
        return x;
    }

    @Override
    public void checkConditions(Functions function) {
        drawPlot(function);
        if (intervalChecker.mayHaveNoRoots(function))
            throw new RuntimeException("Значения функции на концах промежутка одного знака или часть интервала не принадлежит ОДЗ.");
        if (intervalChecker.mayHaveASingleRoot(function))
            log.warn("At this interval, the function may have more than one root!");
    }

    private void drawPlot(Functions function) {
        Series series = new Series(function.getTextView(), function.getFunction(), function.getLeft(), function.getRight());
        Plot plot = new Plot(function.getFunctionName(), series);
        plot.save("./plot");
    }
}
