package com.vinicius.cinema.dtos;

import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.enums.Tecnologia;

import java.math.BigDecimal;
import java.util.Objects;

public class FilmeDTO {

    private Long id;

    private String titulo;

    private String genero;

    private Integer idadeMinima;

    private Integer tempo;

    private BigDecimal valor;

    private Tecnologia tecnologia;

    private Integer poltronasDisponiveis;

    public FilmeDTO(Long id, String titulo, String genero, Integer idadeMinima, Integer tempo, BigDecimal valor, Tecnologia tecnologia, Integer poltronasDisponiveis) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.idadeMinima = idadeMinima;
        this.tempo = tempo;
        this.valor = valor;
        this.tecnologia = tecnologia;
        this.poltronasDisponiveis = poltronasDisponiveis;
    }
    public FilmeDTO() {

    }

    public FilmeDTO(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
        this.idadeMinima = filme.getIdadeMinima();
        this.tempo = filme.getTempo();
        this.valor = filme.getValor();
        this.tecnologia = filme.getTecnologia();
        this.poltronasDisponiveis = filme.getPoltronasDisponiveis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdadeMinima() {
        return idadeMinima;
    }

    public void setIdadeMinima(Integer idadeMinima) {
        this.idadeMinima = idadeMinima;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Integer getPoltronasDisponiveis() {
        return poltronasDisponiveis;
    }

    public void setPoltronasDisponiveis(Integer poltronasDisponiveis) {
        this.poltronasDisponiveis = poltronasDisponiveis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmeDTO filmeDTO = (FilmeDTO) o;
        return Objects.equals(id, filmeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
