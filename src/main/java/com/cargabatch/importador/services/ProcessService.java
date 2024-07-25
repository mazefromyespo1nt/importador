package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.ProcessDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessService {

    private final List<ProcessDTO> procesos;

    public ProcessService() {
        this.procesos = new ArrayList<>();
    }

    public ProcessDTO iniciarProceso(String nombreProceso) {
        ProcessDTO proceso = new ProcessDTO(nombreProceso, new Date(), "Iniciado", "Proceso iniciado");
        procesos.add(proceso);
        return proceso;
    }

    public void actualizarProceso(ProcessDTO proceso, String estado, String detalles) {
        proceso.setEstado(estado);
        proceso.setDetalles(detalles);
        proceso.setFechaEjecucion(new Date()); // Actualiza la fecha a la actual
    }

    public List<ProcessDTO> obtenerProcesos() {
        return new ArrayList<>(procesos); // Retorna una copia de la lista de procesos
    }

}
