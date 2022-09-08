package io.michel.urlshortener.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyGeneratorResponse {
    private String payload;
    private String status;
}
