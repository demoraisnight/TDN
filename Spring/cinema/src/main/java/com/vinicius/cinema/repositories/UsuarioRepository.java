package com.vinicius.cinema.repositories;

import com.vinicius.cinema.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
