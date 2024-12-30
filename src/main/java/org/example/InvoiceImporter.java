package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.Attributes.*;

public class InvoiceImporter implements Importer {
    private static final String NAME_PREFIX = "Dear";
    private static final String AMOUNT_PREFIX = "Amount:";
    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        Map<String, String> attributes = textFile.getAttributes();
        return new Document(attributes);
    }
}
