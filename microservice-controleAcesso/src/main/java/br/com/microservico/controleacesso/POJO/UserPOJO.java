package br.com.microservico.controleacesso.POJO;

import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserPOJO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4829887310009236576L;

	private String userName;
	private String password;
	
	
}
