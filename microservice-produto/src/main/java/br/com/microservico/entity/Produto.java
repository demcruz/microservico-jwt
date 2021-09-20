package br.com.microservico.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;


import br.com.microservico.POJO.ProdutoPOJO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1613351672113609495L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "explanation", nullable = false, length = 255)
	private String explanation;
	
	@Column(name = "name", nullable = false, length = 10)
	private String name;
	
	@Column(name = "price", nullable = false, length = 10)
	private String price;
	
	
	public static Produto create(ProdutoPOJO produtoPOJO) {
		return new ModelMapper().map(produtoPOJO, Produto.class);
	}

}
