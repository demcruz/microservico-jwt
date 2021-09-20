package br.com.microservico.pagamento.POJO;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.microservico.pagamento.entity.ProdutoVenda;
import br.com.microservico.pagamento.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@JsonPropertyOrder({ "id", "idproduto", "quantidade", "venda" })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaPOJO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("idProduto")
	private Integer idProduto;

	@JsonProperty("quantidade")
	private Integer quantidade;

	@JsonProperty("venda")
	private Venda venda;
	
	public static ProdutoVendaPOJO create(ProdutoVenda produtoVenda){
		return new ModelMapper().map(produtoVenda, ProdutoVendaPOJO.class );
	}
	
	

}
