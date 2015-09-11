package com.sistearth.core.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import spark.ResponseTransformer;

public class JsonSerializer implements ResponseTransformer {

    private final ObjectMapper mapper;

    public JsonSerializer() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String render(Object o) throws Exception {
        return mapper.writeValueAsString(o);
    }
}
