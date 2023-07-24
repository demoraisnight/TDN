package com.vinicius.cinema.model;

import com.vinicius.cinema.CinemaApplication;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Filme;
import com.vinicius.cinema.entities.Ingresso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.entities.enums.Role;
import com.vinicius.cinema.entities.enums.Tecnologia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes= CinemaApplication.class)
class ModelTest {

	@DisplayName("Filme Model")
	@Test
	public void getters_and_setters_for_filme_model() {
		Filme filme = new Filme();

		filme.setValor(BigDecimal.valueOf(9.5));
		filme.setTitulo("Duro de Matar");
		filme.setIdadeMinima(16);
		filme.setPoltronasDisponiveis(100);
		filme.setId(1L);
		filme.setTecnologia(Tecnologia.Tec_2D);
		filme.setGenero("Ação");
		filme.setTempo(129);


		assertEquals(filme.getId(),1L);
		assertEquals(filme.getValor(),BigDecimal.valueOf(9.5));
		assertEquals(filme.getTitulo(),"Duro de Matar");
		assertEquals(filme.getIdadeMinima(),16);
		assertEquals(filme.getPoltronasDisponiveis(),100);
		assertEquals(filme.getTecnologia(),Tecnologia.Tec_2D);
		assertEquals(filme.getGenero(),"Ação");
		assertEquals(filme.getTempo(),129);

	}

	@DisplayName("Acesso Model")
	@Test
	public void getters_and_setters_for_acesso_model() {
		Acesso acesso = new Acesso();
		acesso.setId(1L);
		acesso.setUsername("joao");
		acesso.setPassword("123456");

		assertEquals(acesso.getId(),1L);
		assertEquals(acesso.getUsername(),"joao");
		assertEquals(acesso.getPassword(),"123456");

	}
	@DisplayName("Ingresso Model")
	@Test
	public void getters_and_setters_for_ingresso_model() {
		Ingresso ingresso = new Ingresso();
		Usuario user = new Usuario();
		Filme filme = new Filme();
		ingresso.setId(1L);
		ingresso.setFilme(filme);
		ingresso.setUsuario(user);

		assertEquals(ingresso.getId(),1L);
		assertEquals(ingresso.getFilme(),filme);
		assertEquals(ingresso.getUsuario(),user);


	}

	@DisplayName("Usuário Model")
	@Test
	public void getters_and_setters_for_usuario_model() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setIdade(15);
		usuario.setRole(Role.ROLE_CLIENTE);
		usuario.setNome("Júlio");

		assertEquals(usuario.getId(),1L);
		assertEquals(usuario.getIdade(),15);
		assertEquals(usuario.getRole(),Role.ROLE_CLIENTE);
		assertEquals(usuario.getNome(),"Júlio");
	}

}
