package com.cargabatch.importador.services;


import com.cargabatch.importador.DTO.BitacoraDTO;
import com.cargabatch.importador.entitys.Bitacora;
import com.cargabatch.importador.repositorys.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;

    @Autowired
    public BitacoraService(BitacoraRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    public void registrarBitacora(String descripcion) {
        Bitacora bitacora = new Bitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setFecha(new Date());
        bitacoraRepository.save(bitacora);
    }

    public List<BitacoraDTO> getAllBitacoras() {
        List<Bitacora> bitacoras = bitacoraRepository.findAll();
        return bitacoras.stream()
                .map(bitacora -> new BitacoraDTO(
                        bitacora.getId(),
                        bitacora.getFecha(),
                        bitacora.getMensaje(),
                        bitacora.getNivel(),
                        bitacora.getDescripcion()
                ))
                .collect(Collectors.toList());
    }

}
