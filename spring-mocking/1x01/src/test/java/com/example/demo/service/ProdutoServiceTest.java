package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    @Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        Long id = 1L;
        Produto produto = new Produto(id, "Notebook", 2500.0);

        when(produtoRepository.findById(id))
                .thenReturn(Optional.of(produto));

        // Act
        Produto resultado = produtoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        verify(produtoRepository, times(1)).findById(id);
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        Long id = 99L;

        when(produtoRepository.findById(id))
                .thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> produtoService.buscarPorId(id)
        );

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(produtoRepository, times(1)).findById(id);
    }
}
