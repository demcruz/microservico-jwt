package br.com.microservico.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservico.pagamento.POJO.ProdutoVendaPOJO;
import br.com.microservico.pagamento.entity.ProdutoVenda;
import br.com.microservico.pagamento.repository.ProdutoVendaRepository;


@Service
public class ProdutoVendaService {
	
	private final ProdutoVendaRepository produtoVendaRepository;
	
	
	@Autowired
	public ProdutoVendaService(ProdutoVendaRepository produtoVendaRepository) {
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	public ProdutoVendaPOJO create(ProdutoVendaPOJO produtoVendaPOJO) {
		ProdutoVendaPOJO prodVendReturn = ProdutoVendaPOJO.create(produtoVendaRepository.save(ProdutoVenda.create(produtoVendaPOJO)));
		return prodVendReturn;
	}
	
	public Page<ProdutoVendaPOJO> findAll(Pageable pageable){
		var page = produtoVendaRepository.findAll(pageable);
		return page.map(this::convertProdutoVendaPOJO);
	}
	
	private ProdutoVendaPOJO convertProdutoVendaPOJO(ProdutoVenda produtoVenda) {
		return ProdutoVendaPOJO.create(produtoVenda);
	}

}
