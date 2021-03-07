package lab2.io;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class FileEquationWriter implements EquationWriter {
    private Path FILE_NAME;

    public FileEquationWriter(String FILE_NAME) {
        this.FILE_NAME = Paths.get(FILE_NAME);
    }

    @Override
    @SneakyThrows
    public void printInfo(String s) {
        Files.write(FILE_NAME, Collections.singleton(s), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
