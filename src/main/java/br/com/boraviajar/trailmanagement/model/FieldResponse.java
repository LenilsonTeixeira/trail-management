package br.com.boraviajar.trailmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("campo")
    private String field;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("valor")
    private String value;
}
