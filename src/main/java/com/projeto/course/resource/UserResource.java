package com.projeto.course.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.course.entities.User;

/**
 * CLasse resposavel por reucrsos webs
 */

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "/users")
public class UserResource {

	// usada para mapear requisições HTTP do tipo GET 
	@GetMapping
	public ResponseEntity<User> findAll(){
		
		User u = new User(1L, "Maria", "mari@gmail.com","999999","123456");
		return ResponseEntity.ok().body(u);
	}
	
}
