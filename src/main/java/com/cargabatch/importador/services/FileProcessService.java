package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.BitacoraDTO;
import com.cargabatch.importador.entitys.FileEntity;
import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.exceptions.UsuarioServiceException;
import com.cargabatch.importador.repositorys.FileRepository;
import com.cargabatch.importador.repositorys.SucursalesRepositorio;
import com.cargabatch.importador.utils.UtilsDataCells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FileProcessService {

    private final FileRepository fileRepository;
    private final SucursalesService sucursalesService;
    private final UsuarioService usuarioService;
    private final BitacoraService bitacoraService;
    private final String rutaYalgo;

    @Autowired
    public FileProcessService(FileRepository fileRepository, SucursalesService sucursalesService, UsuarioService usuarioService, BitacoraService bitacoraService, @Value("${ruta.yalgo}") String rutaYalgo) {
        this.fileRepository = fileRepository;
        this.sucursalesService = sucursalesService;
        this.usuarioService = usuarioService;
        this.bitacoraService = bitacoraService;
        this.rutaYalgo = rutaYalgo;
    }

    public String validateAndProcessFile(MultipartFile file) throws UsuarioServiceException, IOException {
        // Validar archivo
        String errorValidacion = sucursalesService.validarArchivo(file);
        if (errorValidacion != null) {
            throw new UsuarioServiceException(errorValidacion);
        }

        // Procesar archivo
        try (InputStream inp = file.getInputStream()) {
            if (file.getOriginalFilename().contains("sucursales")) {
                sucursalesService.procesarArchivoExcel(inp);
            } else if (file.getOriginalFilename().contains("usuarios")) {
                usuarioService.procesarArchivoExcel(inp);
            }

            // Guardar el archivo procesado en la base de datos
            FileEntity fileEntity = new FileEntity();
            fileEntity.setNombre(file.getOriginalFilename());
            fileEntity.setFileData(file.getBytes());
            fileEntity.setUploadDate(new Date());
            fileRepository.save(fileEntity);

            // Registrar bitácora del proceso
            bitacoraService.registrarBitacora("Archivo procesado exitosamente: " + file.getOriginalFilename() + " en la ruta " + rutaYalgo);
        }

        return "Señor Stark, el archivo ha sido procesado exitosamente en la ruta " + rutaYalgo;
    }

    public List<BitacoraDTO> getAllBitacoras() {
        return bitacoraService.getAllBitacoras();
    }

    public Optional<FileEntity> saveFileEntity(FileEntity fileEntity) {
        return fileRepository.findById(fileEntity.getId());
    }
}
