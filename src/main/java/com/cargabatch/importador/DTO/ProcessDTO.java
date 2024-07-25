package com.cargabatch.importador.DTO;

import java.util.Date;

public class ProcessDTO {
    private String nombreProceso;
    private Date fechaEjecucion;
    private String estado;
    private String detalles;

    // Constructor
    public ProcessDTO(String nombreProceso, Date fechaEjecucion, String estado, String detalles) {
        this.nombreProceso = nombreProceso;
        this.fechaEjecucion = fechaEjecucion;
        this.estado = estado;
        this.detalles = detalles;
    }

    // Getters y Setters
    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "ProcesoDTO{" +
                "nombreProceso='" + nombreProceso + '\'' +
                ", fechaEjecucion=" + fechaEjecucion +
                ", estado='" + estado + '\'' +
                ", detalles='" + detalles + '\'' +
                '}';

    }

}
