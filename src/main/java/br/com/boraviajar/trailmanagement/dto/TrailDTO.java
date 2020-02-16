package br.com.boraviajar.trailmanagement.dto;

import br.com.boraviajar.trailmanagement.enumeration.DifficultyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @Max(value = 100, message = "O título deve conter no máximo 100 caracteres")
    @Min(value = 5, message = "O título deve conter mais de 5 caracteres")
    @NotEmpty(message = "Título é obrigatório")
    @JsonProperty("titulo")
    private String title;

    @JsonProperty("imagem")
    private String image;

    @Min(value = 150, message="A descrição deve conter pelo menos 150 caracteres")
    @NotEmpty(message = "Descrição obrigatória")
    @JsonProperty("descricao")
    private String description;

    @Max(value = 30, message = "O nome da cidade deve conter no máximo 30 caracteres")
    @NotEmpty(message = "O nome da cidade é obrigatório")
    @JsonProperty("cidade")
    private String city;

    @NotEmpty(message = "O nome do estado é obrigatório")
    @JsonProperty("estado")
    private String state;

    @NotNull(message = "O nível de dificuldade da trilha é obrigatório")
    @JsonProperty("dificuldade")
    private DifficultyType difficulty;

    @NotNull(message = "A data da partida é obrigatório")
    @JsonProperty("dataPartida")
    private LocalDateTime departureDate;

    @NotNull(message = "A data do retorno é obrigatório")
    @JsonProperty("dataRetorno")
    private LocalDateTime returnDate;

    @NotNull(message = "O horário de partida é obrigatório")
    @JsonProperty("horarioPartida")
    private LocalTime departureTime;

    @NotNull(message = "O horário de retorno é obrigatório")
    @JsonProperty("horarioRetorno")
    private LocalTime returnTime;

    @JsonProperty("grupoWhatsapp")
    private String whatsappGroup;
}
