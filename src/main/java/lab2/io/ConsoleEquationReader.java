package lab2.io;

import java.util.Scanner;

public class ConsoleEquationReader implements EquationReader {
    Scanner in = new Scanner(System.in);

    @Override
    public double readDoubleWithMessage(String message) {
        System.out.println(message);
        return readDouble();
    }

    @Override
    public int readInt() {
        return in.nextInt();
    }

    @Override
    public int readIntWithMessage(String message) {
        System.out.println(message);
        return readInt();
    }

    @Override
    public double readDouble() {
        return in.nextDouble();
    }

    @Override
    public String readString() {
        return in.next();
    }

    @Override
    public String readStringWithMessage(String message) {
        System.out.println(message);
        return readString();
    }
}
