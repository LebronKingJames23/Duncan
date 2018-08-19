package com.didispace.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.didispace.domain.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	List<User> findByNameLike(String name);

	List<User> findAll(Specification<User> specification);



}
