package com.example.demo.service;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto adicionarProduto(Produto produto) {
        produto.setId(contadorId++);
        produtos.add(produto);
        return produto;
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                return produto;
            }
        }
        throw new RuntimeException("Produto não encontrado");
    }

    public boolean deletarProduto(Long id) {
        return produtos.removeIf(produto -> produto.getId().equals(id));
    }
}
