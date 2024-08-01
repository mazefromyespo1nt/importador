package com.cargabatch.importador.DTO;

public class DataSheetProductoDTO {
    private String nombre;
    private String descripcion;
    private int codigo;
    private int cantidadTotal;
    private float precioVenta;
    private int tipoProductoId;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public int getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(int cantidadTotal) { this.cantidadTotal = cantidadTotal; }

    public float getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(float precioVenta) { this.precioVenta = precioVenta; }

    public int getTipoProductoId() { return tipoProductoId; }
    public void setTipoProductoId(int tipoProductoId) { this.tipoProductoId = tipoProductoId; }
}
