package lab2.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileEquationReader implements EquationReader {
    private final List<String> lines;
    private int currentLine = 0;

    public FileEquationReader(String FILE_NAME) throws IOException {
        lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(lines.get(currentLine++));
    }

    @Override
    public int readIntWithMessage(String message) {
        return readInt();
    }

    @Override
    public double readDouble() {
        return Double.parseDouble(lines.get(currentLine++).replace(',', '.'));
    }

    @Override
    public double readDoubleWithMessage(String message) {
        return readDouble();
    }

    @Override
    public String readString() {
        return lines.get(currentLine++);
    }

    @Override
    public String readStringWithMessage(String message) {
        return readString();
    }
}
