package com.vinicius.cinema.repositories;

import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    Acesso findByUsername(String username);
}
