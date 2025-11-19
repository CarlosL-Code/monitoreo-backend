package com.carlos.monitoreo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendario_riego")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioRiego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "La fecha de inicio no puede ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @NotNull(message = "La fecha fin no puede ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFin;
    private int frecuenciaHoras;
    private boolean activo = true;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;


    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDate.now();
        }
    }

    //Seguridad en endpoint
    //Jwt

    //cliente de mqtt de java



}
