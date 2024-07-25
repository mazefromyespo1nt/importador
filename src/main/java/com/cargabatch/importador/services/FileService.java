package com.cargabatch.importador.services;
import com.cargabatch.importador.entitys.FileEntity;
import com.cargabatch.importador.repositorys.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import java.util.Date;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity guardarArchivo(MultipartFile file) throws IOException {
        String nombre = file.getOriginalFilename();
        String tipo = file.getContentType();
        byte[] contenido = file.getBytes();
        Long fileSize = (long) contenido.length;
        Date uploadDate = new Date();

        FileEntity fileEntity = new FileEntity(nombre, tipo, contenido, fileSize, uploadDate);
        return fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> obtenerArchivoPorId(Long id) {
        return fileRepository.findById(id);
    }
}


