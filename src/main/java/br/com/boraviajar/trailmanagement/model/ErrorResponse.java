package br.com.boraviajar.trailmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    @JsonProperty("campos")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<FieldResponse> fields = new HashSet<>();

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("erro")
    private String error;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("path")
    private String path;

    public void addField(final FieldResponse field) {
        this.fields.add(field);
    }

}
