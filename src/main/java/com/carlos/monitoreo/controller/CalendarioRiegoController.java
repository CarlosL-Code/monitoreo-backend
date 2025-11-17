package com.carlos.monitoreo.controller;

import com.carlos.monitoreo.models.CalendarioRiego;
import com.carlos.monitoreo.service.CalendarioRiegoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendario")
@CrossOrigin(origins = "*")
public class CalendarioRiegoController {

    private final CalendarioRiegoService service;

    public CalendarioRiegoController(CalendarioRiegoService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<CalendarioRiego> crearCalendario(@RequestBody CalendarioRiego calendario) {
        return ResponseEntity.ok(service.guardar(calendario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CalendarioRiego>> listarCalendario() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<CalendarioRiego>> filtrarPorFechas(
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate inicio,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fin) {
        return ResponseEntity.ok(service.filtrarPorFechas(inicio, fin));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CalendarioRiego> actualizarCalendario(
            @PathVariable Long id, @RequestBody CalendarioRiego calendario) {
        return ResponseEntity.ok(service.actualizar(id, calendario));
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<CalendarioRiego> activarCalendario(@PathVariable Long id){
        return ResponseEntity.ok(service.activar(id));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<CalendarioRiego> desactivarCalendario(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivar(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCalendario(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
