package com.cargabatch.importador.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cargabatch.importador.entitys.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
