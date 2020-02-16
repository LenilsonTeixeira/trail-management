package br.com.boraviajar.trailmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @Size(min = 5, max = 100, message = "O título deve conter mais de 5 caracteres e no máximo 100 caracteres")
    @NotEmpty(message = "Título é obrigatório")
    @JsonProperty("titulo")
    private String title;

    @JsonProperty("imagem")
    private String image;

    @NotEmpty(message = "Descrição obrigatória")
    @JsonProperty("descricao")
    private String description;

    @Size(max = 30, message = "O nome da cidade deve conter no máximo 30 caracteres")
    @NotEmpty(message = "O nome da cidade é obrigatório")
    @JsonProperty("cidade")
    private String city;

    @NotEmpty(message = "O nome do estado é obrigatório")
    @JsonProperty("estado")
    private String state;

    @NotNull(message = "O nível de dificuldade da trilha é obrigatório")
    @JsonProperty("dificuldade")
    private String difficulty;

    @NotNull(message = "A data da partida é obrigatório")
    @JsonProperty("dataPartida")
    private LocalDate departureDate;

    @NotNull(message = "A data do retorno é obrigatório")
    @JsonProperty("dataRetorno")
    private LocalDate returnDate;

    @NotNull(message = "O horário de partida é obrigatório")
    @JsonProperty("horarioPartida")
    private LocalTime departureTime;

    @NotNull(message = "O horário de retorno é obrigatório")
    @JsonProperty("horarioRetorno")
    private LocalTime returnTime;

    @JsonProperty("grupoWhatsapp")
    private String whatsappGroup;
}
