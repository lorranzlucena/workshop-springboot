package com.projeto.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.course.entities.Product;
import com.projeto.course.entities.User;
import com.projeto.course.repositories.ProductRepository;

@Service
public class ProductService {

	/**
	 * ProductService não acessa diretamente o banco, ele usa o ProductRepository.
	 * Ele é o cérebro da aplicação, onde mora a lógica.
	 */
	
	@Autowired
	private ProductRepository repository;

	// Operação na camada de serviço
	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long Id) {
		/**
		 *  representa um valor que pode ou não estar presente. Ele ajuda a evitar NullPointerException 
		 *  e melhora a legibilidade do código ao lidar com valores opcionais.
		 */
		Optional<Product> obj =  repository.findById(Id);
		return obj.get();
		// se colocar assim: 
		//User user = userRepository.findById(id); 
		// Se findById(id) não encontrar o usuário, user será null e o programa quebrará ao chamar getNome().
	}
	
	
}
