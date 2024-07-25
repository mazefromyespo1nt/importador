package com.cargabatch.importador.utils;

import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.entitys.Usuario;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.services.SucursalesService;
import com.cargabatch.importador.services.UsuarioService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Component
public class FileUtils {

    private final UtilsDataCells utilsDataCells;
    private final UsuarioService usuarioService;
    private final SucursalesService sucursalesService;

    @Autowired
    public FileUtils(UtilsDataCells utilsDataCells, UsuarioService usuarioService, SucursalesService sucursalesService) {
        this.utilsDataCells = utilsDataCells;
        this.usuarioService = usuarioService;
        this.sucursalesService = sucursalesService;
    }

    public List<Sucursales> processSucursalesExcelFile(InputStream inputStream) throws IOException {
        List<Sucursales> branchesList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Assuming sucursales sheet is at index 0

        for (Row row : sheet) {
            if (utilsDataCells.esFilaVacia(row)) {
                continue;
            }
            Sucursales sucursal = createSucursalFromRow(row);
            if (sucursal != null) {
                branchesList.add(sucursal);
            }
        }

        if (!branchesList.isEmpty()) {
            sucursalesService.guardarRowsEnBD(branchesList);
        }

        return branchesList;
    }

    private Sucursales createSucursalFromRow(Row row) {
        Sucursales sucursal = new Sucursales();
        try {
            String nombre = utilsDataCells.getCellStringValue(row, 2);
            String direccion = utilsDataCells.getCellStringValue(row, 3);
            int capacidad = utilsDataCells.getCellIntValue(row, 4);

            // Validar datos
            if (!utilsDataCells.validarFormatoTexto(nombre, "^[a-zA-Z\\s]+$") ||
                    !utilsDataCells.validarFormatoTexto(direccion, "^[a-zA-Z0-9\\s]+$") ||
                    !utilsDataCells.validarRangoNumero(capacidad, 1, 1000)) {
                throw new IllegalStateException("Datos inválidos en la fila " + (row.getRowNum() + 1));
            }

            sucursal.setNombre(nombre);
            sucursal.setDireccion(direccion);
            sucursal.setCapacidad(capacidad);
            sucursal.setFechaRegistro(new Date());
            sucursal.setFechaModificacion(new Date());
            sucursal.setStatus(true);
        } catch (IllegalStateException e) {
            return null;
        }
        return sucursal;
    }

    public List<Usuario> processUsuariosExcelFile(InputStream inputStream) throws IOException {
        List<Usuario> usuariosList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(1); // Assuming usuarios sheet is at index 1

        for (Row row : sheet) {
            if (utilsDataCells.esFilaVacia(row)) {
                continue;
            }
            Usuario usuario = createUsuarioFromRow(row);
            if (usuario != null) {
                usuariosList.add(usuario);
            }
        }

        if (!usuariosList.isEmpty()) {
            try {
                usuarioService.validarYGuardarUsuarios((MultipartFile) usuariosList);
            } catch (UsuarioServiceException e) {
                throw new IOException("Error al procesar usuarios: " + e.getMessage());
            }
        }

        return usuariosList;
    }

    private Usuario createUsuarioFromRow(Row row) {
        Usuario usuario = new Usuario();
        try {
            String nombre = utilsDataCells.getCellStringValue(row, 2);
            String apellido = utilsDataCells.getCellStringValue(row, 3);
            String email = utilsDataCells.getCellStringValue(row, 4);
            int telefono = utilsDataCells.getCellIntValue(row, 5);
            Date fechaNacimiento = row.getCell(6).getDateCellValue();

            // Validar datos
            if (!utilsDataCells.validarFormatoTexto(nombre, "^[a-zA-Z\\s]+$") ||
                    !utilsDataCells.validarFormatoTexto(apellido, "^[a-zA-Z\\s]+$") ||
                    !utilsDataCells.validarRangoNumero(telefono, 1000000, 9999999999L)) {
                throw new IllegalStateException("Datos inválidos en la fila " + (row.getRowNum() + 1));
            }

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setTelefono(telefono);
            usuario.setFechaNacimiento(fechaNacimiento);
            // Otros valores necesarios para usuario
            usuario.setAccesCred("");
            usuario.setFechaRegistro(new Date());
            usuario.setFechaModificacion(new Date());
            usuario.setUsuario("");
            usuario.setSucursalId(0);
            usuario.setRolId(0);
            usuario.setStatus((byte) 1);
        } catch (IllegalStateException e) {
            return null;
        }
        return usuario;
    }
}
