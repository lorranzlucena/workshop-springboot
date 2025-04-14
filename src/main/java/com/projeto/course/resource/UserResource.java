package com.projeto.course.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.course.entities.User;
import com.projeto.course.services.UserService;

/**
 * CLasse resposavel por reucrsos webs
 */

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "/users/")
public class UserResource {

	@Autowired
	private UserService service;
	
	
	// usada para mapear requisições HTTP do tipo GET 
	@GetMapping
	public ResponseEntity<List<User>> findAll(){

		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	/**
	 * (@PathVariable : ta dizendo que o Long Id é o valor repassado pela quesição do HTTML
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		
		/**
		 * criar a URI do novo recurso que acabou de ser inserido.
		 * É boa prática REST: ao criar um recurso (POST), você deve responder com 201 Created e a URI do recurso criado.
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
