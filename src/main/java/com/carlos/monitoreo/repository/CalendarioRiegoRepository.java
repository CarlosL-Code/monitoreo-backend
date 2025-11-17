package com.carlos.monitoreo.repository;

import com.carlos.monitoreo.models.CalendarioRiego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalendarioRiegoRepository extends JpaRepository<CalendarioRiego, Long> {

    // Devuelve calendarios cuyo rango intersecta con el intervalo solicitado
    @Query("SELECT c FROM CalendarioRiego c " + "WHERE c.fechaInicio <= :fin AND c.fechaFin >= :inicio " + "ORDER BY c.fechaInicio ASC")
    List<CalendarioRiego> findByRangoFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);
}
