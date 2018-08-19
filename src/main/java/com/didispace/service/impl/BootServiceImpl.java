package com.didispace.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.didispace.domain.User;
import com.didispace.repository.UserRepository;
import com.didispace.service.BootService;
@Service
public class BootServiceImpl implements BootService {
@Autowired
UserRepository userRepository;
@Autowired
RestTemplate restTemplate;
	public BootServiceImpl(UserRepository userRepository) {
	super();
	this.userRepository=userRepository;
}
	//@Cacheable(value="userList")
	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	
	}
	@Override
	public void addUser(User user) {
		
		userRepository.save(user);
	}
	@Override
	public User getUserById(String id) {
		return userRepository.findOne(Integer.valueOf(id));
	}
	@Override
	public User updateUserById(String id, User user) {
		User tempUser=userRepository.findOne(Integer.valueOf(id));
		tempUser.setName(user.getName());
		tempUser.setGender(user.getGender());
		tempUser.setStaffname(user.getStaffname());
		return  userRepository.saveAndFlush(tempUser);
		
	}
	@Override
	public void deleteUserById(String id) {
		userRepository.delete(Integer.valueOf(id));
	}
	@Override
	public List<User> queryByName(String name) {
	
		return userRepository.findByNameLike("%"+name+"%");
	}
	@Override
	public Page<User> pageQuery(Pageable pageable) {
		return userRepository.findAll(pageable);
	
	}
	@Override
	public List<User> queryByKeyword(String keyword) {
		return userRepository.findAll(new Specification<User>(){

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.like(root.get("name"), "%"+keyword+"%"));
                list.add(cb.like(root.get("staffname"), "%"+keyword+"%"));
				return cb.or(list.toArray(new Predicate[list.size()]));
			}
			
		});
	}
	@Override
	public String queryRestTemplate() {
		 ResponseEntity<String> forEntity = restTemplate.getForEntity("https://www.sojson.com/open/api/weather/json.shtml?city=成都", String.class);
		return  forEntity.getBody();
	}

}
