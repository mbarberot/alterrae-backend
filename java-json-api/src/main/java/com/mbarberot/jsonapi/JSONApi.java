package com.mbarberot.jsonapi;


import com.mbarberot.jsonapi.builders.data.JSONApiAttributesBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiDataBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiDataDocumentBuilder;
import com.mbarberot.jsonapi.builders.data.JSONApiRelationshipsBuilder;
import com.mbarberot.jsonapi.builders.error.JSONApiErrorDocumentBuilder;
import com.mbarberot.jsonapi.builders.error.JsonApiErrorBuilder;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class JSONApi {

    public static JSONApiDataDocumentBuilder newDataDocument() {
        return new JSONApiDataDocumentBuilder();
    }

    public static JSONApiErrorDocumentBuilder newErrorDocument() {
        return new JSONApiErrorDocumentBuilder();
    }

    @NoArgsConstructor(access = PRIVATE)
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

    @NoArgsConstructor(access = PRIVATE)
    public static class Error {
        public static JsonApiErrorBuilder newError() {
            return new JsonApiErrorBuilder();
        }

    }
}
