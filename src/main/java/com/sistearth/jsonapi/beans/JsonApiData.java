package com.sistearth.jsonapi.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = false)
@Data
public class JsonApiData extends JsonApiBasicData {
    private Map<String, String> attributes;
    private Map<String, Object> relationships;
    private Map<String, String> links;
}
