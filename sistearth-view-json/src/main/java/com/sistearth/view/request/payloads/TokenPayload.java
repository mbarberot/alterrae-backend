package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;
import com.sistearth.view.request.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload implements Payload {
    private String token;

    @Override
    public boolean isValid() {
        return isNotBlank(token);
    }

    @Override
    public Object getEntity() {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
