package com.vinicius.cinema.services;

import com.vinicius.cinema.dtos.FilmeDTO;
import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.dtos.input.FilmeDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.FilmeRepository;
import com.vinicius.cinema.repositories.IngressoRepository;
import com.vinicius.cinema.repositories.UsuarioRepository;
import com.vinicius.cinema.services.exceptions.DatabaseException;
import com.vinicius.cinema.services.exceptions.IngressosEsgotadosException;
import com.vinicius.cinema.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private AcessoRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private IngressoRepository ingressoRepository;


    @Autowired
    public FilmeService(AcessoRepository usuarioRepository, FilmeRepository filmeRepository, IngressoRepository ingressoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.filmeRepository = filmeRepository;
        this.ingressoRepository = ingressoRepository;

    }


    @Transactional(readOnly = true)
    public Page<FilmeDTO> listar(Pageable pageable) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Acesso acesso = usuarioRepository.findByUsername(username);

        if(acesso.getUsuario().hasRole("ROLE_CLIENTE")){
            List<Filme> listaFilmes = filmeRepository.findByIdadeMinimaLessThanEqual(acesso.getUsuario().getIdade());

            Page<Filme> todosFilmes = new PageImpl<>(listaFilmes, pageable, listaFilmes.size());
            return todosFilmes.map(x -> new FilmeDTO(x));

        }else{
            Page<Filme> todosFilmes = filmeRepository.findAll(pageable);
            return todosFilmes.map(x -> new FilmeDTO(x));
        }

    }

    @Transactional
    public void deleteById(Long id) {
        if( ingressoRepository.findByFilmeId(id).size() != 0){
            throw new DatabaseException("O filme já possui ingressos vendidos");
        }
        else{
            try {
                filmeRepository.deleteById(id);
            }
            catch (EmptyResultDataAccessException e) {
                throw new ResourceNotFoundException("Id não encontrado " + id);
            }
            catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Violação de Integridade");
            }
        }
    }


    @Transactional
    public IngressoDTO comprarIngresso(Long idFilme) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Acesso acesso = usuarioRepository.findByUsername(username);

        Filme filme = searchOrThrow(idFilme);

        if (filme.getPoltronasDisponiveis() >=1){
            filme.setPoltronasDisponiveis(filme.getPoltronasDisponiveis() - 1);
            Ingresso ingresso = new Ingresso();
            ingresso.setFilme(filme);
            ingresso.setUsuario(acesso.getUsuario());

            Ingresso entity = ingressoRepository.save(ingresso);

            return new IngressoDTO(entity);
        }else{
            throw new IngressosEsgotadosException("Não existem mais ingressos disponíveis para esse filme");

        }
    }



    public Filme searchOrThrow(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
    }

    public FilmeDTO inserir(FilmeDTOInp filmeDTOInp) {

        Filme filme = new Filme();
        filme.setPoltronasDisponiveis(100);
        filme.setGenero(filmeDTOInp.getGenero());
        filme.setTempo(filmeDTOInp.getTempo());
        filme.setTecnologia(filmeDTOInp.getTecnologia());
        filme.setIdadeMinima(filmeDTOInp.getIdadeMinima());
        filme.setTitulo(filmeDTOInp.getTitulo());
        filme.setValor(filmeDTOInp.getValor());

        Filme entity = filmeRepository.save(filme);

        return new FilmeDTO(entity);

    }
}
