package br.com.microservico.POJO;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.microservico.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","estoque","preco"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (callSuper = false)
public class ProdutoPOJO extends RepresentationModel<ProdutoPOJO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7693642900383510467L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("explanation")
	private String explanation;

	@JsonProperty("name")
	private String name;
 
	@JsonProperty("price")
	private String price;
	
	
	public static ProdutoPOJO create (Produto produto) {
		return new ModelMapper().map(produto, ProdutoPOJO.class);
	}

}
