package br.com.cod3r.exercicios.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.cod3r.exercicios.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>, PagingAndSortingRepository<Produto, Integer> {
	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
	
	// findByNomeContaining
	// findByNomeIsContaining
	// findByNomeContains
	
	//findByNomeStartsWith
	//findByNomeEndsWith
	
	//findByNomeNotContaining
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome%")
	public Iterable<Produto> searchNameLike(@Param ("nome") String nome);
}
