package com.sistearth.tools.jsonapi;


import com.sistearth.tools.jsonapi.builders.JSONApiAttributesBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiDataDocumentBuilder;
import com.sistearth.tools.jsonapi.builders.JSONApiRelationshipsBuilder;

public class JSONApi {
    public static JSONApiDataDocumentBuilder newDataDocument() {
        return new JSONApiDataDocumentBuilder();
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
}
