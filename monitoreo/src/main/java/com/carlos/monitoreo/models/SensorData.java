package com.carlos.monitoreo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

//Entity indica que es una tabla en la base de datos
@Entity
@Table(name = "sensor_data")
//Data es de lombok, genera automaticamente los getter, setter y toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {

    @Id
    //Genera un id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;
    private Double humedad;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

}
