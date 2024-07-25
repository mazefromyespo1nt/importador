package com.cargabatch.importador.services;

import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.repositorys.SucursalesRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {
    private final SucursalesRepositorio sucursalesRepositorio;

    public WriterService(SucursalesRepositorio sucursalesRepositorio) {
        this.sucursalesRepositorio = sucursalesRepositorio;
    }

    public void saveSucursales(List<Sucursales> sucursalesList) {
        sucursalesRepositorio.saveAll(sucursalesList);
    }
}


//typerepository wildCard

