package br.com.cod3r.exercicios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cod3r.exercicios.model.entities.Produto;
import br.com.cod3r.exercicios.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	//@PostMapping
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Produto salvarProduto(Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	@GetMapping(path ="/pagina/{numeroPagina}")
	public Iterable<Produto> obterProdutosPorPagina(@PathVariable int numeroPagina) {
		Pageable page = PageRequest.of(numeroPagina, 2);
		return produtoRepository.findAll(page);
	}
	@GetMapping(path ="/nome/{parteNome}")
	public Iterable<Produto> obterProdutosPorNome(@PathVariable String parteNome) {
		//return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
		return produtoRepository.searchNameLike(parteNome);
	}
	@GetMapping(path = "/{id}")
	public Optional<Produto> obterProduto(int id) {
		return produtoRepository.findById(id);
	}
//	@PutMapping
//	public Produto alterarProduto(Produto produto) {
//		produtoRepository.save(produto);
//		return produto;
//	}
	@DeleteMapping(path = "/{id}")
	public void excluirProduto(@PathVariable int id){
		produtoRepository.deleteById(id);
	}
}
