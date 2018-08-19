package com.didispace.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.didispace.domain.User;

public interface BootService {

	List<User> getUserList();

	void addUser(User user);

	User getUserById(String id);

	User updateUserById(String id, User user);

	void deleteUserById(String id);

	List<User> queryByName(String name);

	Page<User> pageQuery(Pageable pageable);

	List<User> queryByKeyword(String keyword);

	String queryRestTemplate();

}
