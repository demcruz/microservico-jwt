package br.com.microservico.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservico.pagamento.POJO.ProdutoPOJO;
import br.com.microservico.pagamento.entity.Produto;
import br.com.microservico.pagamento.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public ProdutoPOJO create(ProdutoPOJO produtoPOJO) {
		ProdutoPOJO prodReturn = ProdutoPOJO.create(produtoRepository.save(Produto.create(produtoPOJO)));
		return prodReturn;
	}
	
	public Page<ProdutoPOJO> findAll(Pageable pageable){
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoPOJO);
	}
	
	private ProdutoPOJO convertToProdutoPOJO(Produto produto) {
		return ProdutoPOJO.create(produto);
	}
}
