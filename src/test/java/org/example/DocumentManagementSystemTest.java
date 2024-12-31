package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentManagementSystemTest {
    private static final String RESOURCE_PATH = "src/test/resources/";
    private static final String LETTER_PATH = RESOURCE_PATH + "patient.letter";

    private DocumentManagementSystem documentManagementSystem = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        documentManagementSystem.importFile(LETTER_PATH);
        Document document = getOnlyOneDocument();
        AssertAttributesEquals(document, Attributes.PATH, LETTER_PATH);
    }

    private void AssertAttributesEquals(final Document document,
                                        final String attributeName,
                                        final String expectedValue) {
        String errorMessage = "Document has the wrong value for" + attributeName;
        assertEquals(expectedValue, document.getAttributeValue(attributeName), errorMessage);
    }

    private Document getOnlyOneDocument() {
        List<Document> documents = documentManagementSystem.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }
}