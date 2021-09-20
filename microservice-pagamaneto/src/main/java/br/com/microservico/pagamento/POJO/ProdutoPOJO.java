package br.com.microservico.pagamento.POJO;

import java.io.Serializable;


import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.microservico.pagamento.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({ "id", "estoque"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoPOJO extends RepresentationModel<ProdutoPOJO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671290137997105723L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("estoque")
	private Integer estoque;
	
	public static ProdutoPOJO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoPOJO.class);
	}
	
	

}
