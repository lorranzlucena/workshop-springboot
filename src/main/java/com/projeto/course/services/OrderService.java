package com.projeto.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.course.entities.Order;
import com.projeto.course.entities.User;
import com.projeto.course.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long Id) {
		Optional<Order> obj =  repository.findById(Id);
		return obj.get();
	}
	
	
}
