package com.cargabatch.importador.repositorys;


import com.cargabatch.importador.entitys.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos, Long> {
}
