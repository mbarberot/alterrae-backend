package com.sistearth.backend.views.legacy;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        return render2(o);
    }

    public String render2(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }


}
