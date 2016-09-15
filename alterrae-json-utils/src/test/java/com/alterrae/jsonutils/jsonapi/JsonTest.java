package com.alterrae.jsonutils.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

public class JsonTest {
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    public String jsonify(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
