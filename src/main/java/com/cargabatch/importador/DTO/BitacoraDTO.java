package com.cargabatch.importador.DTO;

import java.util.Date;

public class BitacoraDTO {

    private Long id;
    private Date fecha;
    private String mensaje;
    private String nivel;
    private String descripcion;

    // Constructor
    public BitacoraDTO(Long id, Date fecha, String mensaje, String nivel, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.descripcion = descripcion;
    }





    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion(){
      return descripcion;}

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;}



    @Override
    public String toString() {
        return "BitacoraDTO{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", mensaje='" + mensaje + '\'' +
                ", nivel='" + nivel + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}