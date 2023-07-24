package com.vinicius.cinema.repositories;

import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    List<Ingresso> findByFilmeId(Long id); //pode ser ao contrário idFilme -> tenho q descobrir

    List<Ingresso> findByUsuario(Usuario usuario);
}
