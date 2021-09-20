package br.com.microservico.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservico.POJO.ProdutoPOJO;
import br.com.microservico.entity.Produto;
import br.com.microservico.exception.ResourceNotFoundException;
import br.com.microservico.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public ProdutoPOJO create(ProdutoPOJO produtoPOJO) {
		ProdutoPOJO produPOJO = ProdutoPOJO.create(produtoRepository.save(Produto.create(produtoPOJO)));
		return produPOJO;
	}

	public Page<ProdutoPOJO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoPOJO);
	}

	private ProdutoPOJO convertToProdutoPOJO(Produto produto) {
		return ProdutoPOJO.create(produto);
	}

	public ProdutoPOJO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return ProdutoPOJO.create(entity);
	}

	public ProdutoPOJO update(ProdutoPOJO produtoPOJO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoPOJO.getId());
		if (!optionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		return ProdutoPOJO.create(produtoRepository.save(Produto.create(produtoPOJO)));

	}

	public void Delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		produtoRepository.delete(entity);
	}
}
