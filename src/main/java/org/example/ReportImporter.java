package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.Attributes.*;

public class ReportImporter implements Importer {
    private static final String NAME_PREFIX = "Patient: ";
    private static final int LINE_BODY = 2;

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        textFile.addLines(LINE_BODY, String::isEmpty, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }
}
