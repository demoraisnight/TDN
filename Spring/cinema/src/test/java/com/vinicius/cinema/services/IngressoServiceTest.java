package com.vinicius.cinema.services;

import com.vinicius.cinema.CinemaApplication;
import com.vinicius.cinema.dtos.FilmeDTO;
import com.vinicius.cinema.dtos.IngressoDTO;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.entities.enums.Role;
import com.vinicius.cinema.entities.enums.Tecnologia;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.IngressoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= CinemaApplication.class)
class IngressoServiceTest {

	// @InjectMocks
	@Autowired
	private IngressoService ingressoService;

    @MockBean
    private IngressoRepository ingressoRepository;

    @MockBean
    private AcessoRepository usuarioRepository;


    @DisplayName("Get Ingressos by User")
    @Test
    public void testListar_ValidUsuario_ReturnsIngressoDTOPage() {
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
        usuario.setRole(Role.ROLE_CLIENTE);
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

        ingresso1.setId(1L);
        ingresso1.setFilme(filme1);
        ingresso1.setUsuario(usuario);

        ingresso2.setId(2L);
        ingresso2.setFilme(filme2);
        ingresso2.setUsuario(usuario);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("testUser", "testPassword"));
        Pageable pageable = PageRequest.of(0, 10);


        List<Ingresso> ingressos = new ArrayList<Ingresso>(Arrays.asList(ingresso1, ingresso2));
        when(usuarioRepository.findByUsername("testUser")).thenReturn(acesso);
        when(ingressoRepository.findByUsuario(usuario)).thenReturn(ingressos);


        //Executando o Serviço
        Page<IngressoDTO> result = ingressoService.listar(pageable);


        //Checando se os usuários e os filmes são os mesmos dos ingressos 1 e 2
        assertEquals(ingresso1.getId(), result.getContent().get(0).getId());
        assertEquals(ingresso1.getUsuario().getNome(), result.getContent().get(0).getUsuario().getNome());
        assertEquals(new FilmeDTO(ingresso1.getFilme()), result.getContent().get(0).getFilme());

        assertEquals(ingresso1.getId(), result.getContent().get(0).getId());
        assertEquals(ingresso2.getUsuario().getNome(), result.getContent().get(1).getUsuario().getNome());
        assertEquals(new FilmeDTO(ingresso2.getFilme()), result.getContent().get(1).getFilme());


        //Checando a quantidade de ingressos de determinado usuário
        assertEquals(ingressos.size(), result.getContent().size());

    }




}