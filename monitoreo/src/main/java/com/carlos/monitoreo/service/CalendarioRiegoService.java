package com.carlos.monitoreo.service;

import com.carlos.monitoreo.models.CalendarioRiego;
import com.carlos.monitoreo.repository.CalendarioRiegoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CalendarioRiegoService {

    private final CalendarioRiegoRepository repository;

    public CalendarioRiegoService(CalendarioRiegoRepository repository) {
        this.repository = repository;
    }

    public CalendarioRiego guardar(CalendarioRiego calendario) {
        return repository.save(calendario);
    }

    public List<CalendarioRiego> listar() {
        return repository.findAll();
    }

    public List<CalendarioRiego> filtrarPorFechas(LocalDate inicio, LocalDate fin) {
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        return repository.findByRangoFechas(inicio, fin);
    }

    public CalendarioRiego actualizar(Long id, CalendarioRiego calendarioNuevo) {
        return repository.findById(id).map(
                c -> {
                    c.setFechaInicio(calendarioNuevo.getFechaInicio());
                    c.setFechaFin(calendarioNuevo.getFechaFin());
                    c.setFrecuenciaHoras(calendarioNuevo.getFrecuenciaHoras());
                    c.setActivo(calendarioNuevo.isActivo());
                    return repository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Calendario no encontrado"));
    }

    public CalendarioRiego activar(Long id){
        return repository.findById(id).map(c->{
            c.setActivo(true);
            return repository.save(c);
        }).orElseThrow(() -> new RuntimeException("Calendario no encontrado"));
    }

    public CalendarioRiego desactivar(Long id) {
        return repository.findById(id)
                .map(c -> {
                    c.setActivo(false);
                    return repository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Calendario no encontrado"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Map<String, Object> obtenerNotificacion(){

        LocalDate hoy = LocalDate.now();
        List<CalendarioRiego> calendarios = repository.findActivosParaHoy(hoy);

        Map<String, Object> respuesta = new HashMap<>();

        if(calendarios.isEmpty()){
            respuesta.put("notificacion",false);
            return respuesta;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = hoy.format(formatter);

        CalendarioRiego riego = calendarios.get(0);

        respuesta.put("notificacion",true);
        respuesta.put("mensaje","Hoy se realizara un riego cada " + riego.getFrecuenciaHoras()+" horas.");
        respuesta.put("tipo","info");
        respuesta.put("fecha",fechaFormateada);

        return respuesta;
    }
}
