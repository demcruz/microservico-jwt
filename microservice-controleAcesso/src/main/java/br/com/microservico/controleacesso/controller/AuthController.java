package br.com.microservico.controleacesso.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservico.controleacesso.POJO.UserPOJO;
import br.com.microservico.controleacesso.jwt.JwtTokenProvider;
import br.com.microservico.controleacesso.repository.UserRepository;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/login")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/testeSecurity")
	public String teste() {
			return "testado";
		}
	
	
	
	

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> login(@RequestBody UserPOJO userPOJO) {
		try {
			var userName = userPOJO.getUserName();
			var password = userPOJO.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			
			var user = userRepository.findByUserName(userName);
			
			var token = "";
			
			if(user != null) {
				token = jwtTokenProvider.createToken(userName, user.getRoles());
			}else {
				throw new UsernameNotFoundException("User name not found");
			}
			
			Map<Object, Object> model = new HashMap<>();
					model.put("username", userName);
					model.put("token", token);
					return ok(model);
			
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalido nome / pass word");
			
		}
	}

	

}
