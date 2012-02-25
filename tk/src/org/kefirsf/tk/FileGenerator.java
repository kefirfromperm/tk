package org.kefirsf.tk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Generate new file with unique name
 *
 * @author Vitalii Samolovskikh aka Kefir
 */
public class FileGenerator {
    private final Random random;
    private File directory;

    public FileGenerator() {
        random = new Random(System.nanoTime());
    }

    public File newFile() throws IOException {
        validate();

        File file;
        int count = 0;
        do {
            count++;
            file = new File(directory, generateName() + ".png");
        } while (!file.createNewFile() && count < 10);

        return file;
    }

    /**
     * Generate random file name
     *
     * @return random char sequence
     */
    private String generateName() {
        int id;
        synchronized (random) {
            id = 1+Math.abs(random.nextInt(1000000000));
        }

        StringBuilder name = new StringBuilder();

        while (id > 0) {
            int c = id % 36;
            id = id / 36;
            if (c < 26) {
                name.append((char)('a' + (char) c));
            } else {
                name.append((char)('0' + (char) c - 26));
            }
        }

        return name.toString();
    }

    /**
     * Validate the directory. Create it if absent. Check for write.
     *
     * @throws IOException if the directory is absent or I can't create files in the directory.
     */
    private void validate() throws IOException {
        if (directory == null) {
            throw new IllegalStateException("Please, set the directory.");
        }
        if (!directory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            directory.mkdir();
        }

        if (!directory.exists()) {
            throw new FileNotFoundException("Directory not found.");
        }

        if (!directory.isDirectory()) {
            throw new IOException("The directory is not a directory.");
        }

        if (!directory.canWrite()) {
            throw new IOException("Can't write to directory.");
        }
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
