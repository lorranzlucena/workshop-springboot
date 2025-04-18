package com.projeto.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projeto.course.entities.Category;
import com.projeto.course.entities.Order;
import com.projeto.course.entities.OrderItem;
import com.projeto.course.entities.Payment;
import com.projeto.course.entities.Product;
import com.projeto.course.entities.User;
import com.projeto.course.entities.enums.OrderStatus;
import com.projeto.course.repositories.CategoryRepository;
import com.projeto.course.repositories.OrderItemRepository;
import com.projeto.course.repositories.OrderRepository;
import com.projeto.course.repositories.ProductRepository;
import com.projeto.course.repositories.UserRepository;

/**
 * classe substitui o tradicional arquivo XML de configuração. Ela é usada para
 * definir Beans, configurar dependências e personalizar o comportamento do
 * Spring
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final OrderItemRepository orderItemRepository_1;

	@Autowired
	private OrderRepository orderRepository;

	// injeção de dependência automática
	// Spring crie e gerencie objetos sem que você precise instanciá-los manualmente
	// com new.
	// Isso reduz o acoplamento entre classes e torna o código mais limpo e
	// flexível.
	@Autowired // so com essa anotação ele ja faz a dependencia com o userRepository
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	


    TestConfig(OrderItemRepository orderItemRepository_1) {
        this.orderItemRepository_1 = orderItemRepository_1;
    }
	

	// CommandLineRunner : serve para inicar classe assim que o sistema iniciar
	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);

		/**
		 * O método saveAll(List<T> entities) do JpaRepository recebe uma lista de
		 * entidades e salva todas no banco de dados ao mesmo tempo. Eficiência: Salva
		 * vários registros de uma vez, reduzindo o número de transações no banco.
		 * 
		 */
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		//o1 = pedido 1
		//p1 = produto 1
		//2  = quantidade 		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 


		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z") , o1);
		// para salvar um objeto OneToOne o payment n tera o reposotory.nesse caso chamo o do repository do order.
		o1.setPayment(pay1);
		orderRepository.save(o1);
	
	}
}
