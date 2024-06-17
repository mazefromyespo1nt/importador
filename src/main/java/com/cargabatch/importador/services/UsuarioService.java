package com.cargabatch.importador.services;

import com.cargabatch.importador.entitys.Usuario;
import com.cargabatch.importador.repositorys.UsuarioRepositorio;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public String validarYGuardarUsuarios(MultipartFile file) throws IOException {
        String mensajeValidacion = validarArchivo(file);
        if (mensajeValidacion != null) {
            return mensajeValidacion;
        }

        List<Usuario> usuarios = procesarArchivoExcel(file);

        if (usuarios == null) {
            return "Error al procesar el archivo";
        }

        guardarUsuariosEnBD(usuarios);

        return "Señor Stark, el archivo ha sido procesado exitosamente";
    }

    private String validarArchivo(MultipartFile file) {
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

    private List<Usuario> procesarArchivoExcel(MultipartFile file) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Usuario> usuarios = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Usuario usuario = crearUsuarioDesdeFila(row);
                if (usuario == null) {
                    return null; // Manejar datos inválidos o incompletos
                }

                usuarios.add(usuario);
            }
            return usuarios;
        } catch (IOException e) {
            return null; // Manejar error al procesar el archivo
        }
    }

    private Usuario crearUsuarioDesdeFila(Row row) {
        Usuario usuario = new Usuario();
        try {
            for (int i = 0; i < 3; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) {
                    return null; // Manejar datos inválidos o incompletos
                }
                switch (i) {
                    case 0:
                        usuario.setId((long) cell.getNumericCellValue());
                        break;
                    case 1:
                        usuario.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        usuario.setEmail(cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }
        } catch (IllegalStateException e) {
            return null; // Manejar error en los datos del archivo
        }
        return usuario;
    }

    private void guardarUsuariosEnBD(List<Usuario> usuarios) {
        usuarioRepositorio.saveAll(usuarios);
    }
}
