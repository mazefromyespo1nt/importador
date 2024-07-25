package com.cargabatch.importador.entitys;

import com.cargabatch.importador.repositorys.FileRepository;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Lob
    @Column(name = "contenido", nullable = false)
    private byte[] contenido;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    // Constructor por defecto
    public FileEntity() {
    }

    // Constructor con parámetros
    public FileEntity(String nombre, String tipo, byte[] contenido, Long fileSize, Date uploadDate) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setFileData(byte[] contenido) {
        this.contenido = contenido;
        this.fileSize = (long) contenido.length;
        this.uploadDate = new Date();
    }

    // Método para guardar la entidad de archivo
    public void saveFileEntity(FileRepository fileRepository) {
        fileRepository.save(this);
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fileSize=" + fileSize +
                ", uploadDate=" + uploadDate +
                '}';
    }
}