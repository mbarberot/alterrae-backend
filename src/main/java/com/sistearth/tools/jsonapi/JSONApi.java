package com.sistearth.tools.jsonapi;


import com.sistearth.tools.jsonapi.builders.data.JSONApiAttributesBuilder;
import com.sistearth.tools.jsonapi.builders.data.JSONApiDataBuilder;
import com.sistearth.tools.jsonapi.builders.data.JSONApiDataDocumentBuilder;
import com.sistearth.tools.jsonapi.builders.data.JSONApiRelationshipsBuilder;
import com.sistearth.tools.jsonapi.builders.error.JSONApiErrorDocumentBuilder;
import com.sistearth.tools.jsonapi.builders.error.JsonApiErrorBuilder;

public class JSONApi {
    private JSONApi() {
    }

    public static JSONApiDataDocumentBuilder newDataDocument() {
        return new JSONApiDataDocumentBuilder();
    }

    public static JSONApiErrorDocumentBuilder newErrorDocument() {
        return new JSONApiErrorDocumentBuilder();
    }
    public static class Data {
        private Data() {
        }
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

        private Error() {
        }
        public static JsonApiErrorBuilder newError() {
            return new JsonApiErrorBuilder();
        }

    }
}
