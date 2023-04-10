package comp3350.fitclub.Utilities;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import comp3350.fitclub.application.InitializePersistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/SC.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");

        Path original = Paths.get(DB_SRC.getAbsolutePath());
        Path newFilePath = Paths.get(target.getAbsolutePath());
        Files.copy(original, newFilePath, REPLACE_EXISTING);
        InitializePersistence.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}

