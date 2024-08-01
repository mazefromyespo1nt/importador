package com.cargabatch.importador.entitys;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @Column(name = "usuario_id")
    private int usuarioId;

    @Column(name = "cantidad_total")
    private int cantidadTotal;

    @Column(name = "precio_venta")
    private float precioVenta;

    @Column(name = "proveedor_id")
    private int proveedorId;

    @Column(name = "tipo_producto_id", length = 11)
    private int tipoProductoId;

    @Column(name = "status")
    private boolean status;

    public Productos() {
        // Constructor por defecto
    }

    public Productos(String nombre, String descripcion, int codigo, Date fechaRegistro, Date fechaModificacion, int usuarioId, int cantidadTotal, float precioVenta, int proveedorId, int tipoProductoId, boolean status) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.usuarioId = usuarioId;
        this.cantidadTotal = cantidadTotal;
        this.precioVenta = precioVenta;
        this.proveedorId = proveedorId;
        this.tipoProductoId = tipoProductoId;
        this.status = status;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(int tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo=" + codigo +
                ", cantidadTotal=" + cantidadTotal +
                ", precioVenta=" + precioVenta +
                ", tipoProductoId='" + tipoProductoId + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", status=" + status +
                '}';
    }


}