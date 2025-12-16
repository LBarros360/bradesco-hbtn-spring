package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Usuario usuario = new Usuario(id, "João", "Av. Brasil, 100");

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioService.buscarUsuarioPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João", resultado.getNome());
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        // Arrange
        Long id = 99L;

        when(usuarioRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usuarioService.buscarUsuarioPorId(id)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        // Arrange
        Usuario usuario = new Usuario(1L, "Maria", "Rua das Flores, 200");

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.salvarUsuario(usuario);

        // Assert
        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }
}
