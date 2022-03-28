package br.com.sistemaloja.sistemaloja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemaloja.sistemaloja.model.Loja;
import br.com.sistemaloja.sistemaloja.model.Roupa;

@Repository
public interface RoupaRepository extends CrudRepository<Roupa, Long> {

	Iterable<Roupa>findByLoja(Roupa loja);
	
	Roupa findByCodigo(long codigo);
	
	List<Roupa> findByNomeRoupa(String nomeRoupa);
	
	@Query(value = "select u from Roupa u where u.nomeRoupa like %?1%")
	List<Roupa> findByNomesRoupa(String nomeRoupa);

	Iterable<Roupa> findByLoja(Loja loja);
}
