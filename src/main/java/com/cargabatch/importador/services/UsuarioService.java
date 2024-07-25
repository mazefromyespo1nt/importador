package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.DataSheetUserDTO;
import com.cargabatch.importador.entitys.Usuario;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.repositorys.UsuarioRepositorio;
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
public class UsuarioService {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }


    public String validarYGuardarUsuarios(MultipartFile file) throws UsuarioServiceException {
        try (InputStream inputStream = file.getInputStream()) {
            procesarArchivoExcel(inputStream);
            return "Señor Stark, el archivo ha sido procesado exitosamente";
        } catch (IOException e) {
            throw new UsuarioServiceException("Error al procesar el archivo");
        }
    }

    public List<Usuario> procesarArchivoExcel(InputStream file) throws IOException {
        var usuarioArrayList = new ArrayList<Usuario>();
        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet = wb.getSheetAt(1);

        for (Row row : sheet) {
            if (row.getRowNum() <= 1) {
                continue;
            }
            if (row.getCell(2) == null && row.getCell(3) == null && row.getCell(6) == null) {
                break;
            }
            var dto = crearDTODesdeFila(row);
            if (dto != null) {
                var usu = convertirDTOAEntidad(dto);
                usuarioArrayList.add(usu);
            }
        }
        guardarRowsEnBD(usuarioArrayList);
        return usuarioArrayList;
    }

    private DataSheetUserDTO crearDTODesdeFila(Row row) {
        try {
            String nombre = row.getCell(2).getStringCellValue();
            String apellido = row.getCell(3).getStringCellValue();
            String email = row.getCell(4).getStringCellValue();
            int telefono = (int) row.getCell(5).getNumericCellValue();
            Date fechaNacimiento = row.getCell(6).getDateCellValue();

            return new DataSheetUserDTO(nombre, apellido, email, telefono, fechaNacimiento);
        } catch (IllegalStateException e) {
            return null; // Maneja error en los datos del archivo
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

    private Usuario convertirDTOAEntidad(DataSheetUserDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        // Otros valores necesarios
        usuario.setAccesCred("");
        usuario.setFechaRegistro(new Date());
        usuario.setFechaModificacion(new Date());
        usuario.setUsuario("");
        usuario.setSucursalId(0);
        usuario.setRolId(0);
        usuario.setStatus((byte) 1);
        return usuario;
    }

    public void guardarRowsEnBD(List<Usuario> usuarios) {
        usuarioRepositorio.saveAll(usuarios);
    }
}

