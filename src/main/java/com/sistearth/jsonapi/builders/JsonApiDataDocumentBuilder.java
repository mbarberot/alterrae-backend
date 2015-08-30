package com.sistearth.jsonapi.builders;

import com.sistearth.jsonapi.beans.JsonApiDataDocument;

public class JsonApiDataDocumentBuilder implements JsonApiBuilder {
    private final JsonApiDataDocument document;

    public JsonApiDataDocumentBuilder() {
        document = new JsonApiDataDocument();
    }

    public JsonApiDataDocumentBuilder data(JsonApiDataBuilder data) {
        document.setData(data.build());
        return this;
    }

    @Override
    public JsonApiDataDocument build() {
        return document;
    }
}
