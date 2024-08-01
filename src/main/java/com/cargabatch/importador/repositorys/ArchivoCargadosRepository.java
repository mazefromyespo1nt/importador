package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ArchivosCargados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoCargadosRepository extends JpaRepository<ArchivosCargados, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}

