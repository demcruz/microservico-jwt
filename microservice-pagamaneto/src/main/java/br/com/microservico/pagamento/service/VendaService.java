package br.com.microservico.pagamento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.microservico.pagamento.POJO.VendaPOJO;
import br.com.microservico.pagamento.entity.ProdutoVenda;
import br.com.microservico.pagamento.entity.Venda;
import br.com.microservico.pagamento.exception.ResourceNotFoundException;
import br.com.microservico.pagamento.repository.ProdutoRepository;
import br.com.microservico.pagamento.repository.ProdutoVendaRepository;
import br.com.microservico.pagamento.repository.VendaRepository;

@Service
public class VendaService {
	
	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;
	
	
	@Autowired
	public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = vendaRepository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	
	public VendaPOJO create(VendaPOJO vendaPOJO) {
		Venda venda = vendaRepository.save(Venda.create(vendaPOJO));
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaPOJO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		
		venda.setProdutos(produtosSalvos);
		return VendaPOJO.create(venda);
	}
	
	public Page<VendaPOJO> findAll(Pageable pageable){
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaPOJO);
	}
	
	private VendaPOJO convertToVendaPOJO(Venda venda) {
		return VendaPOJO.create(venda);
	}
	
	public VendaPOJO findById(Long id){
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records fond thi id"));
		return VendaPOJO.create(entity);
	}
	
	
	
	

}
