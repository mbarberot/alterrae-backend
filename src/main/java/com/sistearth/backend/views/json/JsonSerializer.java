package com.sistearth.backend.views.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonSerializer {

    private final ObjectMapper mapper;

    public JsonSerializer() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String render(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
