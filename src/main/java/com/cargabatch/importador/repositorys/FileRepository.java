package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.FileEntity;
import com.cargabatch.importador.entitys.Sucursales;
import com.cargabatch.importador.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}

