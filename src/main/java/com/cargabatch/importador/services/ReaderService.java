package com.cargabatch.importador.services;

import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.entitys.Usuario;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.utils.CellValidationsENUM;
import com.cargabatch.importador.utils.FileUtils;
import com.cargabatch.importador.utils.UtilsDataCells;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReaderService {

    private final UsuarioService usuarioService;
    private final SucursalesService sucursalesService;
    private final FileUtils fileUtils;
    private final UtilsDataCells utilsDataCells;

    @Autowired
    public ReaderService(UsuarioService usuarioService, SucursalesService sucursalesService, FileUtils fileUtils, UtilsDataCells utilsDataCells) {
        this.usuarioService = usuarioService;
        this.sucursalesService = sucursalesService;
        this.fileUtils = fileUtils;
        this.utilsDataCells = utilsDataCells;
    }

    public String validarYProcesarUsuariosYSucursales(InputStream inputStream) throws UsuarioServiceException {
        try {
            List<Usuario> usuariosList = fileUtils.processUsuariosExcelFile(inputStream);
            usuarioService.guardarRowsEnBD(usuariosList);

            List<Sucursales> sucursalesList = fileUtils.processSucursalesExcelFile(inputStream);
            sucursalesService.guardarRowsEnBD(sucursalesList);

            return "Señor Stark, los archivos han sido procesados exitosamente";
        } catch (IOException e) {
            throw new UsuarioServiceException("Error al procesar el archivo: " + e.getMessage());
        }
    }


    //procesa el archivo de sucursales
    public void procesarArchivoSucursales(InputStream inputStream) throws UsuarioServiceException {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Sucursales> sucursales = new ArrayList<>();

            for (Row row : sheet) {
                if (utilsDataCells.esFilaVacia(row)) continue;

                Cell nameCell = row.getCell(0);
                Cell addressCell = row.getCell(1);

                if (!utilsDataCells.validarCelda(nameCell, CellValidationsENUM.VALIDATE_NON_EMPTY)) {
                    throw new UsuarioServiceException("Nombre de sucursal inválido en la fila " + row.getRowNum());
                }

                if (!utilsDataCells.validarCelda(addressCell, CellValidationsENUM.VALIDATE_NON_EMPTY)) {
                    throw new UsuarioServiceException("Dirección de sucursal inválida en la fila " + row.getRowNum());
                }

                String name = utilsDataCells.obtenerValorCeldaComoString(nameCell);
                String address = utilsDataCells.obtenerValorCeldaComoString(addressCell);

                // Crea una nueva sucursal y la añade a la lista
                Sucursales sucursal = new Sucursales();
                sucursal.setNombre(name);
                sucursal.setDireccion(address);
                sucursales.add(sucursal);
            }

            sucursalesService.guardarRowsEnBD(sucursales);
        } catch (IOException e) {
            throw new UsuarioServiceException("Error al leer el archivo de sucursales: " + e.getMessage());
        }
    }
}