package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.Attributes.*;

public class LetterImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final int LINE_ADDRESS = 2;
    private static final String END_BODY_WORD = "regards,";

    @Override
    public Document importFile(File file) throws IOException {
       final TextFile textFile = new TextFile(file);

       textFile.addLineSuffix(NAME_PREFIX, PATIENT);

       final int lineNumber = textFile.addLines(LINE_ADDRESS, String::isEmpty, ADDRESS);
       textFile.addLines(lineNumber + 1,
               line -> line.startsWith(END_BODY_WORD),
               BODY);

       final Map<String, String> attributes = textFile.getAttributes();
       attributes.put(TYPE, "LETTER");
       return new Document(attributes);
    }
}
