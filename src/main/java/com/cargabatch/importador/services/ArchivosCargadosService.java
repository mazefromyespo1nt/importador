package com.cargabatch.importador.services;


import com.cargabatch.importador.entitys.ArchivosCargados;
import com.cargabatch.importador.repositorys.ArchivoCargadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

@Service
public class ArchivosCargadosService {

    @Autowired
    private ArchivoCargadosRepository archivoCargadosRepository;

    public ArchivosCargados saveArchivosCargados(String fileName, InputStream fileInputStream, String filePath) {
        ArchivosCargados archivosCargados = new ArchivosCargados();
        archivosCargados.setNombreArchivo(fileName);
        archivosCargados.setRuta(filePath);
        archivosCargados.setExtArchivo(fileName.substring(fileName.lastIndexOf(".")));
        archivosCargados.setFechaRegistro(new Date());
        archivosCargados.setStatus(true);

        return archivoCargadosRepository.save(archivosCargados);
    }
}
