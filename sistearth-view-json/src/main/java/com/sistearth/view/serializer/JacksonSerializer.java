package com.sistearth.view.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonSerializer {

    private final ObjectMapper mapper;

    public JacksonSerializer() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String render(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
