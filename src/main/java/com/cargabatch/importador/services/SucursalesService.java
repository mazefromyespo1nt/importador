package com.cargabatch.importador.services;
import com.cargabatch.importador.DTO.SucursalesDTO;
import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.repositorys.SucursalesRepositorio;
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
public class SucursalesService {
    private final SucursalesRepositorio sucursalesRepositorio;

    public SucursalesService(SucursalesRepositorio sucursalesRepositorio) {
        this.sucursalesRepositorio = sucursalesRepositorio;
    }

    public String validarYGuardarSucursales(MultipartFile file) throws UsuarioServiceException {

        return "Señor Stark, el archivo ha sido procesado exitosamente";
    }


    String validarArchivo(MultipartFile file) {
        String nombreArchivo = file.getOriginalFilename();
        if (nombreArchivo == null || !nombreArchivo.matches("nombredelformato_\\d{8}.xlsx")) {
            return "El nombre del archivo no tiene el formato que se espera";
        }
        String fechaArchivo = nombreArchivo.substring(14, 22);
        String fechaActual = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (!fechaArchivo.equals(fechaActual)) {
            return "La fecha del archivo no coincide con la fecha actual";
        }
        return null;
    }

    public List<Sucursales> procesarArchivoExcel(InputStream file) throws IOException {
        var sucursalesArrayList =  new ArrayList<Sucursales>();
        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) {
                continue;
            }
            if (row.getCell(2) == null && row.getCell(3) == null && row.getCell(4) == null) {
                break;
            }
            var sucu = crearUsuarioDesdeFila(row);
            System.out.println(sucu.getCapacidad());
            sucursalesArrayList.add(sucu);
        }
        guardarRowsEnBD(sucursalesArrayList);
        return sucursalesArrayList;
    }

    private Sucursales crearUsuarioDesdeFila(Row row) {
        Sucursales sucursales = new Sucursales();

        try {
            for (int i = 2; i <= 4; i++) {

                Cell cell = row.getCell(i);
                if (cell == null) {
                    return null; // Maneja datos inválidos o incompletos
                }
                if (i == 2) {
                    sucursales.setNombre(cell.getStringCellValue());

                } else if (i == 3) {
                    sucursales.setDireccion(cell.getStringCellValue());
                } else if (i == 4) {
                    sucursales.setCapacidad((int) cell.getNumericCellValue());
                }
            }
        } catch (IllegalStateException e) {
            return null; // Maneja error en los datos del archivo
        }
        return sucursales;
    }



    public void guardarRowsEnBD(List<Sucursales> sucursales) {
        sucursalesRepositorio.saveAll(sucursales);
    }
}
