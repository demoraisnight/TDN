package com.vinicius.cinema.entities;

import com.vinicius.cinema.entities.enums.Tecnologia;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "tb_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String genero;

    private Integer idadeMinima;

    private Integer tempo;

    private BigDecimal valor;

    private Tecnologia tecnologia;

    private Integer poltronasDisponiveis;

    public Filme(Long id, String titulo, String genero, Integer idadeMinima, Integer tempo, BigDecimal valor, Tecnologia tecnologia, Integer poltronasDisponiveis) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.idadeMinima = idadeMinima;
        this.tempo = tempo;
        this.valor = valor;
        this.tecnologia = tecnologia;
        this.poltronasDisponiveis = poltronasDisponiveis;
    }

    public Filme() {
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
}

