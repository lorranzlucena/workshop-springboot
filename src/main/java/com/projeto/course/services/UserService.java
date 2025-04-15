package com.projeto.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.course.entities.User;
import com.projeto.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	// Operação na camada de serviço
	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long Id) {
		/**
		 *  representa um valor que pode ou não estar presente. Ele ajuda a evitar NullPointerException 
		 *  e melhora a legibilidade do código ao lidar com valores opcionais.
		 */
		Optional<User> obj =  repository.findById(Id);
		return obj.get();
		// se colocar assim: 
		//User user = userRepository.findById(id); 
		// Se findById(id) não encontrar o usuário, user será null e o programa quebrará ao chamar getNome().
	}
	
	public User insert(User obj) {
		
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		
		// aqui apenas vai monitorar o objeto pra poder trabalha com ele depois.
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	/** 
	 * metodo resposnavel por atualizar os dados do entity, com os dados que chegou do obj 
	 */
	private void updateData(User entity, User obj) {
		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());

	}
	
}
