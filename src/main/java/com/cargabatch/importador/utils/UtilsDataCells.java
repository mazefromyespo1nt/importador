package com.cargabatch.importador.utils;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;



@Component
public class UtilsDataCells {

    public String validarArchivo(MultipartFile file) {
        // Validar el nombre del archivo
        String errorNombre = validarNombreArchivo(file);
        if (errorNombre != null) {
            return errorNombre;
        }

        // Validar contenido del archivo
        try (InputStream inp = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inp);
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null || sheet.getPhysicalNumberOfRows() == 0) {
                return "El archivo está vacío o no tiene contenido válido";
            }
            // Puedes agregar más validaciones específicas sobre el contenido aquí
        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }

        return null; // No hay errores
    }


    public String validarNombreArchivo(MultipartFile file) {
        String nombreArchivo = file.getOriginalFilename();
        if (nombreArchivo == null || !nombreArchivo.matches("nombredelformato_\\d{8}.xlsx")) {
            return "El nombre del archivo no tiene el formato que se espera";
        }
        return null;
    }

    public boolean esFilaVacia(Row row) {
        if (row == null) {
            return true;
        }
        for (int cn = row.getFirstCellNum(); cn < row.getLastCellNum(); cn++) {
            Cell cell = row.getCell(cn);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }


    public String obtenerValorCeldaComoString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // Evalúa la fórmula si es necesario
            default:
                return "";
        }
    }

    public String getCellStringValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        return obtenerValorCeldaComoString(cell);
    }
    public int getCellIntValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
            throw new IllegalArgumentException("La celda no es válida o no contiene un valor numérico");
        }
        return (int) cell.getNumericCellValue();
    }

    public int obtenerValorCeldaComoInt(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        throw new IllegalStateException("La celda no contiene un valor numérico");
    }

    public float obtenerValorCeldaComoFloat(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (float) cell.getNumericCellValue();
        }
        throw new IllegalStateException("La celda no contiene un valor numérico");
    }

    public boolean validarFormatoTexto(String texto, String regex) {
        return texto != null && texto.matches(regex);
    }

    public boolean validarRangoNumero(double numero, double min, double max) {
        return numero >= min && numero <= max;
    }

    public boolean validarCelda(Cell cell, CellValidationsENUM validationType) {
        if (cell == null) {
            return false;
        }

        switch (validationType) {
            case VALIDATE_NON_EMPTY:
                return cell.getCellType() != CellType.BLANK;
            case VALIDATE_NUMERIC:
                return cell.getCellType() == CellType.NUMERIC;
            case VALIDATE_TEXT:
                return cell.getCellType() == CellType.STRING;
            case VALIDATE_DATE:
                return cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell);
            case VALIDATE_EMAIL:
                // Implementa la lógica de validación de email
                return cell.getCellType() == CellType.STRING && cell.getStringCellValue().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            case VALIDATE_PHONE:
                // Implementa la lógica de validación de teléfono
                return cell.getCellType() == CellType.NUMERIC && String.valueOf((long) cell.getNumericCellValue()).matches("\\d{10}");
            case VALIDATE_CUSTOM:
                // Implementa lógica de validación personalizada
                return true;
            default:
                return false;
        }
    }
}
