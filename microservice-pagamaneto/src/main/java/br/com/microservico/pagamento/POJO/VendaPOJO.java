package br.com.microservico.pagamento.POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

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

@JsonPropertyOrder({ "id", "data", "produtos", "valorTotal" })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaPOJO extends RepresentationModel<VendaPOJO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5456966274109026396L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("data")
	private Date data;
	
	@JsonProperty("produtos")
	private List<ProdutoVendaPOJO> produtos;

	@JsonProperty("valorTotal")
	private Double valorTotal;
	
	public static VendaPOJO create(Venda venda) {
		return new ModelMapper().map(venda, VendaPOJO.class);
	}

}
