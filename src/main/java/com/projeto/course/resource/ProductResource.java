package com.projeto.course.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.course.entities.Product;
import com.projeto.course.entities.User;
import com.projeto.course.services.ProductService;

/**
 * CLasse resposavel por reucrsos webs
 */

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "/products/")
public class ProductResource {

	@Autowired
	private ProductService service;
	
	// usada para mapear requisições HTTP do tipo GET 
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){

		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	/**
	 * (@PathVariable : ta dizendo que o Long Id é o valor repassado pela quesição do HTTML
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
