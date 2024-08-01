package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ArchivosCargados;
import com.cargabatch.importador.entitys.InvocacionProcessoBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface InvocacionPBatchRepository extends JpaRepository<InvocacionProcessoBatch, Long> {
    }
