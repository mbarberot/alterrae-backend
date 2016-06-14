package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload implements Payload<String> {
    private String token;

    @Override
    public String getEntity() {
        return token;
    }
}
