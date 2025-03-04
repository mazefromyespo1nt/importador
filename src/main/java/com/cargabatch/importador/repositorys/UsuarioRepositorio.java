package com.cargabatch.importador.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cargabatch.importador.entitys.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
