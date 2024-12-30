package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
    private final Map<String, Importer> extensionToImporter = new HashMap<>();
    List<Document> documents;

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());

        documents = new ArrayList<>();
    }

    public void importFile(final String path) throws IOException {
        final File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf('.');
        if (separatorIndex == -1) {
            throw new UnknownFileTypeException("No extension found for file: " + path);
        }
        if (separatorIndex == path.length() - 1) {
            throw new UnknownFileTypeException("No extension found for file: " + path);
        }

        final String extension = path.substring(separatorIndex + 1);
        final Importer importer = extensionToImporter.get(extension);
        if (importer == null) {
            throw new UnknownFileTypeException("For file:" + path);
        }
        final Document document = importer.importFile(file);
        documents.add(document);
    }
}
