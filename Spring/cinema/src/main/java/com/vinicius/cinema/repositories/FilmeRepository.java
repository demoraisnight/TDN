package com.vinicius.cinema.repositories;

import com.vinicius.cinema.dtos.FilmeDTO;
import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByIdadeMinimaLessThanEqual(Integer idade);
}
