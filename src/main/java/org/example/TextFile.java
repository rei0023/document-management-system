package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Predicate;

import static org.example.Attributes.*;

class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;

    TextFile(final File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.readAllLines(file.toPath());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void addLineSuffix(final String prefix, final String attributeName) {
        for (final String line: this.lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }

    public int addLines(final int startIndex,
                        final Predicate<String> isEnd,
                        final String attributeName) {
        final StringBuilder accumulator = new StringBuilder();
        int lineNumber;
        for (lineNumber = startIndex; lineNumber < lines.size(); lineNumber++) {
            final String line = lines.get(lineNumber);
            if (isEnd.test(line)) {
                break;
            }
            accumulator.append(line);
            accumulator.append(System.lineSeparator());
        }
        attributes.put(attributeName, accumulator.toString().trim());
        return lineNumber;
    }
}
