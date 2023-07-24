package com.vinicius.cinema.services;

import com.vinicius.cinema.dtos.FilmeDTO;
import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.dtos.input.FilmeDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.entities.enums.Role;
import com.vinicius.cinema.entities.enums.Tecnologia;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.FilmeRepository;
import com.vinicius.cinema.repositories.IngressoRepository;
import com.vinicius.cinema.services.exceptions.DatabaseException;
import com.vinicius.cinema.services.exceptions.IngressosEsgotadosException;
import com.vinicius.cinema.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FilmeServiceTest {

    @MockBean
    private AcessoRepository usuarioRepository;

    @MockBean
    private FilmeRepository filmeRepository;

    @MockBean
    private IngressoRepository ingressoRepository;


    @Autowired
    private FilmeService filmeService;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(AcessoRepository.class);
        filmeRepository = mock(FilmeRepository.class);
        ingressoRepository = mock(IngressoRepository.class);

        filmeService = new FilmeService(usuarioRepository,filmeRepository,ingressoRepository);
    }

    @Test
    public void testInserir() {
        FilmeDTOInp filmeDTOInp = new FilmeDTOInp();
        Filme filme = new Filme();

        filmeDTOInp.setValor(BigDecimal.valueOf(9.5));
        filmeDTOInp.setTitulo("Duro de Matar");
        filmeDTOInp.setIdadeMinima(16);
        filmeDTOInp.setTecnologia(Tecnologia.Tec_2D);
        filmeDTOInp.setGenero("Ação");
        filmeDTOInp.setTempo(129);

        filme.setValor(BigDecimal.valueOf(9.5));
        filme.setTitulo("Duro de Matar");
        filme.setIdadeMinima(16);
        filme.setPoltronasDisponiveis(100);
        filme.setId(1L);
        filme.setTecnologia(Tecnologia.Tec_2D);
        filme.setGenero("Ação");
        filme.setTempo(129);

        when(filmeRepository.save(any(Filme.class))).thenReturn(filme);

        FilmeDTO result = filmeService.inserir(filmeDTOInp);

        assertNotNull(result);
        assertEquals(filmeDTOInp.getTitulo(), result.getTitulo());
        assertEquals(filmeDTOInp.getIdadeMinima(), result.getIdadeMinima());

    }

    @Test
    public void testListar() {
        Usuario usuario = new Usuario();
        Acesso acesso = new Acesso();
        Filme filme1 = new Filme();
        Filme filme2 = new Filme();
        Ingresso ingresso1 = new Ingresso();
        Ingresso ingresso2 = new Ingresso();

        acesso.setId(1L);
        acesso.setUsuario(usuario);
        acesso.setUsername("testUser");
        acesso.setPassword("testPassword");

        usuario.setId(1L);
        usuario.setIdade(15);
        usuario.setRole(Role.ROLE_FUNCIONARIO);
        usuario.setNome("Júlio");

        filme1.setValor(BigDecimal.valueOf(9.5));
        filme1.setTitulo("Duro de Matar");
        filme1.setIdadeMinima(16);
        filme1.setPoltronasDisponiveis(100);
        filme1.setId(1L);
        filme1.setTecnologia(Tecnologia.Tec_2D);
        filme1.setGenero("Ação");
        filme1.setTempo(129);

        filme2.setValor(BigDecimal.valueOf(9.5));
        filme2.setTitulo("Lord of The Rings");
        filme2.setIdadeMinima(12);
        filme2.setPoltronasDisponiveis(100);
        filme2.setId(2L);
        filme2.setTecnologia(Tecnologia.Tec_3D);
        filme2.setGenero("Aventura");
        filme2.setTempo(169);

        Pageable pageable = PageRequest.of(0, 10);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPassword"));
        List<Filme> listaFilmes = new ArrayList<>(Arrays.asList(filme1,filme2));

        Page<Filme> todosFilmes = new PageImpl<>(listaFilmes, pageable, listaFilmes.size());
        when(usuarioRepository.findByUsername("testUser")).thenReturn(acesso);
        when(filmeRepository.findAll(pageable)).thenReturn(todosFilmes);

        Page<FilmeDTO> result = filmeService.listar(pageable);

        assertEquals(filme1.getId(),result.getContent().get(0).getId());
        assertEquals(filme2.getId(),result.getContent().get(1).getId());
        assertEquals(filme1.getTitulo(),result.getContent().get(0).getTitulo());
        assertEquals(filme2.getTitulo(),result.getContent().get(1).getTitulo());


    }


    @Test
    public void testDeleteByIdComIngressosVendidos() {
        List<Ingresso> listaDeIngressos = new ArrayList<>();
        listaDeIngressos.add(new Ingresso());
        when(ingressoRepository.findByFilmeId(1L)).thenReturn(listaDeIngressos);

        assertThrows(DatabaseException.class, () -> filmeService.deleteById(1L));

    }



    @Test
    public void testComprarIngressoEmFilmeComPoltronasDisponiveis() {

        Integer poltronasDisponiveis = 50;
        Acesso acesso = new Acesso();
        acesso.setUsername("testUser");
        acesso.setPassword("testPassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");
        acesso.setUsuario(usuario);
        Ingresso ingresso = new Ingresso();
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setPoltronasDisponiveis(poltronasDisponiveis);
        ingresso.setFilme(filme);
        ingresso.setUsuario(usuario);

        FilmeService filmeServicespy = spy(new FilmeService(usuarioRepository,filmeRepository,ingressoRepository));

        when(usuarioRepository.findByUsername("testUser")).thenReturn(acesso);
        when(ingressoRepository.save(any(Ingresso.class))).thenReturn(ingresso);
        doReturn(filme).when(filmeServicespy).searchOrThrow(5L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPassword"));

        IngressoDTO result= filmeServicespy.comprarIngresso(5L);

        assertEquals(ingresso.getFilme().getId(), result.getFilme().getId());
        assertEquals(poltronasDisponiveis, result.getFilme().getPoltronasDisponiveis()+1);

    }

    @Test
    public void testComprarIngressoEmFilmeComSemPoltronasDisponiveis() {

        Integer poltronasDisponiveis = 0;
        Acesso acesso = new Acesso();
        acesso.setUsername("testUser");
        acesso.setPassword("testPassword");
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");
        acesso.setUsuario(usuario);
        Ingresso ingresso = new Ingresso();
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setPoltronasDisponiveis(poltronasDisponiveis);
        ingresso.setFilme(filme);
        ingresso.setUsuario(usuario);

        FilmeService filmeServicespy = spy(new FilmeService(usuarioRepository,filmeRepository,ingressoRepository));

        when(usuarioRepository.findByUsername("testUser")).thenReturn(acesso);
        when(ingressoRepository.save(any(Ingresso.class))).thenReturn(ingresso);
        doReturn(filme).when(filmeServicespy).searchOrThrow(5L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPassword"));


        assertThrows(IngressosEsgotadosException.class, () -> filmeServicespy.comprarIngresso(5L));
        assertEquals(poltronasDisponiveis, filme.getPoltronasDisponiveis());

    }

    @Test
    public void testSearchOrThrow_ShouldThrowResourceErrorWhenNotFound() {
        when(filmeRepository.findById(5L)).thenThrow(new ResourceNotFoundException("Filme não encontrado"));


        assertThrows(ResourceNotFoundException.class, () -> filmeService.searchOrThrow(5L));
    }

}
