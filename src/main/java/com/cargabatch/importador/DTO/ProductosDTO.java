package com.cargabatch.importador.DTO;

import java.util.Date;

public class ProductosDTO {

    private int id_producto;
    private String nombre;
    private String descripcion;
    private int codigo;
    private Date fecha_registro;
    private Date fecha_modificacion;
    private int usuario_id;
    private int cantidad_total;
    private float precio_venta;
    private int provedor_id;
    private String tipo_producto_id;
    private boolean status;



    public ProductosDTO(int id_producto, String nombre, String descripcion, int codigo, Date fecha_registro, Date fecha_modificacion, int usuario_id, int cantidad_total, float precio_venta, int provedor_id, String tipo_producto_id, boolean status) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.fecha_registro = fecha_registro;
        this.fecha_modificacion = fecha_modificacion;
        this.usuario_id = usuario_id;
        this.cantidad_total = cantidad_total;
        this.precio_venta = precio_venta;
        this.provedor_id = provedor_id;
        this.tipo_producto_id = tipo_producto_id;
        this.status = status;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_productos) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getProvedor_id() {
        return provedor_id;
    }

    public void setProvedor_id(int provedor_id) {
        this.provedor_id = provedor_id;
    }

    public String getTipo_producto_id() {
        return tipo_producto_id;
    }

    public void setTipo_producto_id(String tipo_producto_id) {
        this.tipo_producto_id = tipo_producto_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

