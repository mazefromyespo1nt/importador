package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
}
