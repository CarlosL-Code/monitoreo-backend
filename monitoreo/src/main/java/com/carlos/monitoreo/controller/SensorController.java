package com.carlos.monitoreo.controller;

import com.carlos.monitoreo.models.SensorData;
import com.carlos.monitoreo.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "*")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<SensorData> registrar(@RequestBody SensorData data) {
        return ResponseEntity.ok(service.registrar(data));
    }

    @GetMapping("/ultimos")
    public ResponseEntity<List<SensorData>> ultimos() {
        return ResponseEntity.ok(service.obtenerUltimos());
    }

    @GetMapping("/historial")
    public ResponseEntity<List<SensorData>> historial() {
        return ResponseEntity.ok(service.obtenerHistorial());
    }

}
