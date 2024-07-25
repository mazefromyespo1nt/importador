package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Sucursales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalesRepositorio extends JpaRepository<Sucursales, Long> {


}
