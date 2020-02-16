package br.com.boraviajar.trailmanagement.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DifficultyType {

    EASY("Fácil"),
    MODERATE("Moderado"),
    HARD("Difícil");

    private String description;

    public static DifficultyType from(final String value) {

        return Stream
                .of(DifficultyType.values())
                .filter(difficulty -> Objects.equals(difficulty.description, value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Tipo de dificuldade %s não encontrado", value)));
    }
}
