package br.com.microservico.controleacesso;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.microservico.controleacesso.entity.Permission;
import br.com.microservico.controleacesso.entity.User;
import br.com.microservico.controleacesso.repository.PermissionRepository;
import br.com.microservico.controleacesso.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceControleAcessoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceControleAcessoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, passwordEncoder);
		};

	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		
		Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		if (findPermission == null) {
			permission = new Permission();
			permission.setDescription("User");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}
		
		User admin = new User(); 
		admin.setUserName("Hulk");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(passwordEncoder.encode("1234567"));
		admin.setPermissions(Arrays.asList(permission));
		

		User find = userRepository.findByUserName("Hulk");
		if (find == null) {
			userRepository.save(admin);
		}
	}

}
