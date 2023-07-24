package com.vinicius.cinema.services;

import com.vinicius.cinema.CinemaApplication;
import com.vinicius.cinema.dtos.AcessoDTO;
import com.vinicius.cinema.dtos.UsuarioDTO;
import com.vinicius.cinema.dtos.input.AcessoDTOInp;
import com.vinicius.cinema.entities.Acesso;
import com.vinicius.cinema.entities.Usuario;
import com.vinicius.cinema.entities.enums.Role;
import com.vinicius.cinema.entities.enums.Tecnologia;
import com.vinicius.cinema.repositories.AcessoRepository;
import com.vinicius.cinema.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= CinemaApplication.class)
public class AcessoServiceTest {


    private AcessoService acessoService;

    private AcessoRepository acessoRepository;

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        acessoRepository = mock(AcessoRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        acessoService = new AcessoService(passwordEncoder, acessoRepository, usuarioRepository);
    }

    @Test
    public void testInserir() {
        // Mock input data
        AcessoDTOInp acessoDTOInp = new AcessoDTOInp();
        acessoDTOInp.setNomeUsuario("John Doe");
        acessoDTOInp.setRole(Role.ROLE_CLIENTE);
        acessoDTOInp.setIdade(30);
        acessoDTOInp.setUsername("john_doe");
        acessoDTOInp.setPassword("password");

        // Mock the save calls
        Usuario savedUsuario = new Usuario();
        savedUsuario.setId(1L);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(savedUsuario);

        Acesso savedAcesso = new Acesso();
        savedAcesso.setId(1L);
        savedAcesso.setUsername("john_doe");
        when(acessoRepository.save(any(Acesso.class))).thenReturn(savedAcesso);

        // Mock the password encoder
        String encodedPassword = "encoded_password";
        when(passwordEncoder.encode(acessoDTOInp.getPassword())).thenReturn(encodedPassword);

        // Call the method to be tested
        AcessoDTO result = acessoService.inserir(acessoDTOInp);

        // Assertions
        assertNotNull(result);
        assertEquals(savedAcesso.getId(), result.getId());
        assertEquals(acessoDTOInp.getUsername(), result.getUsername());
        assertNotNull(result.getUsuarioDTO());
        assertEquals(new UsuarioDTO(savedUsuario).getNome(), result.getUsuarioDTO().getNome());
    }

}
