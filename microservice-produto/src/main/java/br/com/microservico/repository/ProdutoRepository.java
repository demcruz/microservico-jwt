package br.com.microservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.microservico.entity.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
