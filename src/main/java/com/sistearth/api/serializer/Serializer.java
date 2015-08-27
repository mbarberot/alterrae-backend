package com.sistearth.api.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Serializer<T> {
    private String prefix;

    public Serializer(String prefix) {
        this.prefix = prefix;
    }

    public String serialize(List<T> entities) throws SerializerException {
        Map<String, List<T>> map = newHashMap(ImmutableMap.of(prefix, entities));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, map);
            return sw.toString();
        } catch (IOException e) {
            throw new SerializerException("Failed to serialize", e);
        }
    }
}
