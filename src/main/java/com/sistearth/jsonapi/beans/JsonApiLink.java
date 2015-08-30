package com.sistearth.jsonapi.beans;

import lombok.Data;

import java.util.Map;

@Data
public class JsonApiLink {
    Map<String,String> links;
}
