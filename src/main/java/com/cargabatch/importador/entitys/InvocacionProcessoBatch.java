package com.cargabatch.importador.entitys;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "invocacion_proceso_batch")
public class InvocacionProcessoBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoca_proceso")
    private int idInvocaProceso;

    @Column(name = "fecha_proceso")
    @Temporal(TemporalType.DATE)
    private Date fechaProceso;

    @Column(name = "id_archivo")
    private int idArchivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_proceso")
    private TipoProceso tipoProceso;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "no_registros_procesados")
    private int noRegistrosProcesados;

    @Column(name = "status")
    private boolean status;

    // Getters y Setters
    public int getIdInvocaProceso() {
        return idInvocaProceso;
    }

    public void setIdInvocaProceso(int idInvocaProceso) {
        this.idInvocaProceso = idInvocaProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public TipoProceso getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProceso tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getNoRegistrosProcesados() {
        return noRegistrosProcesados;
    }

    public void setNoRegistrosProcesados(int noRegistrosProcesados) {
        this.noRegistrosProcesados = noRegistrosProcesados;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public enum TipoProceso {
        tipo1, TIPO2, TIPO3 // Añadir los valores necesarios
    }
}

