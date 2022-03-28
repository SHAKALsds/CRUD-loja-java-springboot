package br.com.sistemaloja.sistemaloja.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemaloja.sistemaloja.model.Loja;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long>{

	Loja findById(long id);
	
	List<Loja> findByNomeLoja(String nomeLoja);
	
	@Query(value = "select u from Loja u where u.nomeLoja like %?1%")
	List<Loja>findByNomesLoja(String nomeLoja);
	
}

