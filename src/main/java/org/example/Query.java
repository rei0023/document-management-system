package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Query implements Predicate<Document> {
    Map<String, String> clauses;

    static Query parse(final String query) {
        Map<String, String> clauses = new HashMap<>();
        String[] split = query.split(",");
        for (String s : split) {
            String[] pair = s.split(":");
            clauses.put(pair[0], pair[1]);
        }
        return new Query(clauses);
    }

    private Query(final Map<String, String> clauses) {
        this.clauses = clauses;
    }

    @Override
    public boolean test(Document document) {
        Set<Map.Entry<String, String>> pairs = clauses.entrySet();
        for (Map.Entry<String, String> pair : pairs) {
            String documentValue = document.getAttributeValue(pair.getKey());
            String queryValue = pair.getValue();
            if (documentValue == null || !documentValue.contains(queryValue)) {
                return false;
            }
        }
        return true;
    }
}
