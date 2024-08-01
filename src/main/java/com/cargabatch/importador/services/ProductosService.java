package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.DataSheetProductoDTO;
import com.cargabatch.importador.entitys.Productos;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.repositorys.ProductosRepositorio;
import com.cargabatch.importador.utils.CellValidationsENUM;
import com.cargabatch.importador.utils.FileUtils;
import com.cargabatch.importador.utils.UtilsDataCells;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductosService {

    private final ProductosRepositorio productosRepositorio;
    private final UtilsDataCells utilsDataCells;
    private final FileUtils fileUtils;

    public ProductosService(ProductosRepositorio productosRepositorio, UtilsDataCells utilsDataCells, FileUtils fileUtils) {
        this.productosRepositorio = productosRepositorio;
        this.utilsDataCells = utilsDataCells;
        this.fileUtils = fileUtils;
    }

    public String validarYGuardarProductos(MultipartFile file) throws UsuarioServiceException {
        try (InputStream inputStream = file.getInputStream()) {
            procesarArchivoExcel();
            return "Señor Stark, el archivo ha sido procesado exitosamente";
        } catch (IOException e) {
            throw new UsuarioServiceException("Error al procesar el archivo");
        }
    }

    public List<Productos> procesarArchivoExcel() throws IOException {
        var file = fileUtils.readFileByPath("/LAYOUTS_GYM_05232024Pro.xlsx");
        var productosArrayList = new ArrayList<Productos>();
        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(2);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) {
                continue;
            }
            if (row.getCell(2) == null && row.getCell(3) == null && row.getCell(7) == null) {
                break;
            }
            var dto = crearDTODesdeFila(row);
            if (dto != null) {
                var pro = convertirDTOAEntidad(dto);
                productosArrayList.add(pro);
            }
        }
        guardarRowsEnBD(productosArrayList);
        return productosArrayList;
    }

    private DataSheetProductoDTO crearDTODesdeFila(Row row) {
        try {
            if (!utilsDataCells.validarCelda(row.getCell(2), CellValidationsENUM.VALIDATE_TEXT)) {
                throw new IllegalStateException("Error en la celda de nombre");
            }
            String nombre = utilsDataCells.obtenerValorCeldaComoString(row.getCell(2));

            if (!utilsDataCells.validarCelda(row.getCell(3), CellValidationsENUM.VALIDATE_TEXT)) {
                throw new IllegalStateException("Error en la celda de descripción");
            }
            String descripcion = utilsDataCells.obtenerValorCeldaComoString(row.getCell(3));

            if (!utilsDataCells.validarCelda(row.getCell(4), CellValidationsENUM.VALIDATE_NUMERIC)) {
                throw new IllegalStateException("Error en la celda de código");
            }
            int codigo = utilsDataCells.obtenerValorCeldaComoInt(row.getCell(4));

            if (!utilsDataCells.validarCelda(row.getCell(5), CellValidationsENUM.VALIDATE_NUMERIC)) {
                throw new IllegalStateException("Error en la celda de cantidad total");
            }
            int cantidad_total = utilsDataCells.obtenerValorCeldaComoInt(row.getCell(5));

            if (!utilsDataCells.validarCelda(row.getCell(6), CellValidationsENUM.VALIDATE_NUMERIC)) {
                throw new IllegalStateException("Error en la celda de precio de venta");
            }
            float precio_venta = utilsDataCells.obtenerValorCeldaComoFloat(row.getCell(6));

            if (!utilsDataCells.validarCelda(row.getCell(7), CellValidationsENUM.VALIDATE_NUMERIC)) {
                throw new IllegalStateException("Error en la celda de tipo de producto");
            }
            int tipo_producto_id = utilsDataCells.obtenerValorCeldaComoInt(row.getCell(7));

             DataSheetProductoDTO dto = new DataSheetProductoDTO();
            dto.setNombre(nombre);
            dto.setDescripcion(descripcion);
            dto.setCodigo(codigo);
            dto.setCantidadTotal(cantidad_total);
            dto.setPrecioVenta(precio_venta);
            dto.setTipoProductoId(tipo_producto_id);

            return dto;
        } catch (IllegalStateException e) {
            // Maneja error en los datos del archivo
            return null;
        }
    }



    public String validarFechaArchivo(MultipartFile file) {
        String nombreArchivo = file.getOriginalFilename();
        String fechaArchivo = nombreArchivo.substring(14, 22);
        String fechaActual = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!fechaArchivo.equals(fechaActual)) {
            return "La fecha del archivo no coincide con la fecha actual";
        }
        return null;
    }

    private Productos convertirDTOAEntidad(DataSheetProductoDTO dto) {
        Productos productos = new Productos();
        productos.setNombre(dto.getNombre());

        productos.setDescripcion(dto.getDescripcion());
        productos.setCodigo(dto.getCodigo());
        productos.setCantidadTotal(dto.getCantidadTotal());
        productos.setPrecioVenta(dto.getPrecioVenta());
        productos.setTipoProductoId(dto.getTipoProductoId());

        // Asignaciones adicionales
        productos.setProveedorId(0);
        productos.setFechaRegistro(new Date());
        productos.setFechaModificacion(new Date());
        productos.setUsuarioId(0);
        productos.setStatus(true);

        return productos;
    }


    public void guardarRowsEnBD(List<Productos> productosList) {

        productosList.forEach(producto -> System.out.println("Guardando producto: " + producto));
        productosRepositorio.saveAll(productosList);
    }
    }














