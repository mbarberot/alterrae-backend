package com.mbarberot.jsonapi;


import com.mbarberot.jsonapi.builders.data.JSONApiAttributesBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiDataBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiDataDocumentBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiRelationshipsBuilder;
import com.mbarberot.jsonapi.builders.error.JSONApiErrorDocumentBuilder;
import com.mbarberot.jsonapi.builders.error.JsonApiErrorBuilder;

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
