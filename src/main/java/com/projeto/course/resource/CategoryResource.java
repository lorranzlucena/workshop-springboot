package com.projeto.course.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.course.entities.Category;
import com.projeto.course.entities.User;
import com.projeto.course.services.CategoryService;

/**
 * CLasse resposavel por reucrsos webs
 */

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "/categories/")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	
	// usada para mapear requisições HTTP do tipo GET 
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){

		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	/**
	 * (@PathVariable : ta dizendo que o Long Id é o valor repassado pela quesição do HTTML
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
