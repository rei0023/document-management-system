package org.example;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private final Map<String, String> attributes;

    Document(final Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttributeValue(final String attributeName) {
        return attributes.get(attributeName);
    }
}
