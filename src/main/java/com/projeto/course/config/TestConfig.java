package com.projeto.course.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projeto.course.entities.Order;
import com.projeto.course.entities.User;
import com.projeto.course.repositories.OrderRepository;
import com.projeto.course.repositories.UserRepository;

/**
 *classe  substitui o tradicional arquivo XML de configuração. Ela é usada para definir Beans, 
 * configurar dependências e personalizar o comportamento do Spring
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;
	
	//injeção de dependência automática
	// Spring crie e gerencie objetos sem que você precise instanciá-los manualmente com new.
	//Isso reduz o acoplamento entre classes e torna o código mais limpo e flexível.
	@Autowired  // so com essa anotação ele ja faz a dependencia com o userRepository
	private UserRepository userRepository;


	//CommandLineRunner : serve para inicar classe assim que o sistema iniciar
	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);
		
		
		
		/**
		 * O método saveAll(List<T> entities) do JpaRepository recebe uma lista de entidades e salva todas no banco de dados ao mesmo tempo.
		 * Eficiência: Salva vários registros de uma vez, reduzindo o número de transações no banco.
		 * 
		 */
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		
	}
	
}
