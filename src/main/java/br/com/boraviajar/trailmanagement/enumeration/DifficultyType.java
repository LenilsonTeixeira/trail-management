package br.com.boraviajar.trailmanagement.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DifficultyType {

    EASY("Fácil"),
    MODERATE("Moderado"),
    HARD("Difícil");

    private String description;
}
