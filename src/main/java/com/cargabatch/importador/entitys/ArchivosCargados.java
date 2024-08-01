package com.cargabatch.importador.entitys;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "archivos_cargados")
public class ArchivosCargados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo_carga")
    private int idArchivoCarga;

    @Column(name = "nombre_archivo", length = 80, nullable = false)
    private String nombreArchivo;

    @Column(name = "ext_archivo", length = 8, nullable = false)
    private String extArchivo;

    @Column(name = "ruta", length = 256, nullable = false)
    private String ruta;

    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "status", nullable = false)
    private boolean status;

    // Getters and Setters
    public int getIdArchivoCarga() {
        return idArchivoCarga;
    }

    public void setIdArchivoCarga(int idArchivoCarga) {
        this.idArchivoCarga = idArchivoCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getExtArchivo() {
        return extArchivo;
    }

    public void setExtArchivo(String extArchivo) {
        this.extArchivo = extArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
