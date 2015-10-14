package com.sistearth.tools.jsonapi;


import com.sistearth.tools.jsonapi.builders.JSONApiAttributesBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiDataDocumentBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiRelationshipsBuilder;
import com.sistearth.tools.jsonapi.builders.errors.JSONApiErrorDocumentBuilder;
import com.sistearth.tools.jsonapi.builders.errors.JsonApiErrorBuilder;

public class JSONApi {
    public static JSONApiDataDocumentBuilder newDataDocument() {
        return new JSONApiDataDocumentBuilder();
    }

    public static JSONApiErrorDocumentBuilder newErrorDocument() {
        return new JSONApiErrorDocumentBuilder();
    }

    public static class Data {
        public static JSONApiDataBuilder newData(String id, String type) {
            return new JSONApiDataBuilder(id, type);
        }
        public static JSONApiAttributesBuilder newAttributes() {
            return new JSONApiAttributesBuilder();
        }
        public static JSONApiRelationshipsBuilder newRelationships() {
            return new JSONApiRelationshipsBuilder();
        }
    }

    public static class Error {
        public static JsonApiErrorBuilder newError() {
            return new JsonApiErrorBuilder();
        }
    }
}
