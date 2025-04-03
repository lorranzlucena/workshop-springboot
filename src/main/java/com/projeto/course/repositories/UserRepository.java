package com.projeto.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.course.entities.User;

/**
 * o spring dataJPA ja tem uma implementaão padrão pra essa interface, logo não precisa criar as implementações
 * ele ja vem com o CRUD pronto.
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
