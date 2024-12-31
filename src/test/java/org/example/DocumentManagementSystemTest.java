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
    private static final String PATIENT_NAME = "Alan Turing";

    private final DocumentManagementSystem documentManagementSystem = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        documentManagementSystem.importFile(LETTER_PATH);
        Document document = getOnlyOneDocument();
        assertAttributesEquals(document, Attributes.PATH, LETTER_PATH);
    }

    @Test
    public void shouldImportLetterAttributes() throws IOException {
        documentManagementSystem.importFile(LETTER_PATH);

        final Document document = getOnlyOneDocument();

        assertAttributesEquals(document, Attributes.PATIENT, PATIENT_NAME);

        assertAttributesEquals(document, Attributes.ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");

        assertAttributesEquals(document, Attributes.BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29 December 2024 to 5th January 2025.");

        assertTypeIs("LETTER", document);
    }

    private void assertTypeIs(String type, Document document) {
        assertEquals(document.getAttributeValue(Attributes.TYPE), type);
    }

    private void assertAttributesEquals(final Document document,
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