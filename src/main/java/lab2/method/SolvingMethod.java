package lab2.method;

import lab2.function.Functions;

public interface SolvingMethod {
    double solve(Functions function);

    void checkConditions(Functions function);
}
