package com.carlos.monitoreo.repository;

import com.carlos.monitoreo.models.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository maneja las operaciones CRUD automaticamente
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    // Obtener los últimos 10 registros, ordenados por fecha y hora descendente
    List<SensorData> findTop10ByOrderByFechaDescHoraDesc();
}
