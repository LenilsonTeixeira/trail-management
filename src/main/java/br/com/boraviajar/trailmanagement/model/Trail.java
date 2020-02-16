package br.com.boraviajar.trailmanagement.model;

import br.com.boraviajar.trailmanagement.enumeration.DifficultyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trail")
public class Trail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String title;
    private String image;
    private String description;
    private String city;
    private String state;
    private DifficultyType difficulty;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private LocalTime departureTime;
    private LocalTime returnTime;
    private String whatsappGroup;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
