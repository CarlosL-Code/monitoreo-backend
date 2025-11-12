package com.carlos.monitoreo.service;

import com.carlos.monitoreo.models.SensorData;
import com.carlos.monitoreo.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class SensorService {

    private final SensorDataRepository repo;

    public SensorService(SensorDataRepository repo) {
        this.repo = repo;
    }

    public SensorData registrar(SensorData data) {
        data.setFecha(LocalDate.now());
        data.setHora(LocalTime.now());
        return repo.save(data);
    }

    public List<SensorData> obtenerUltimos() {
        return repo.findTop10ByOrderByFechaDescHoraDesc();
    }

    public List<SensorData> obtenerHistorial() {
        return repo.findAll();
    }

}
