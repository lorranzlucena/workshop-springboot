package com.projeto.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.course.entities.Category;
import com.projeto.course.entities.User;
import com.projeto.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	// Operação na camada de serviço
	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Long Id) {
		//serve principalmente para evitar problemas com valores null
		Optional<Category> obj = repository.findById(Id);
		return obj.get();
		// se colocar assim:
		// User user = userRepository.findById(id);
		// Se findById(id) não encontrar o usuário, user será null e o programa quebrará
		// ao chamar getNome().
	}

}
