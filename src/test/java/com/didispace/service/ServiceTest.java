package com.didispace.service;



import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.didispace.domain.User;
import com.didispace.repository.UserRepository;
import com.didispace.service.impl.BootServiceImpl;
import com.google.common.collect.Lists;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
public class ServiceTest {
	BootService bootService;
	@Mock
	UserRepository userRepository;
	@Mock
	Specification<User> specification;
	@Mock
	Pageable pageable;
	@Mock
	Page<User> page;
	@Before
	public void setUp() {
		bootService=new BootServiceImpl(userRepository);
		Mockito.when(userRepository.findAll()).thenReturn(Lists.newArrayList(new User(),new User()));
		Mockito.when(userRepository.findOne(1)).thenReturn(new User(1,"xiaoming"));
		Mockito.when(userRepository.save(new User())).thenReturn(new User());
		Mockito.when(userRepository.saveAndFlush(isA(User.class))).thenAnswer(new Answer<User>(){

			@Override
			public User answer(InvocationOnMock invocation) throws Throwable {
				
				return new User(1,"niuniu");
			}
			
		});
		page=new PageImpl(Lists.newArrayList(new User(),new User()));
		
		Mockito.when(userRepository.findByNameLike("xiaoming")).thenAnswer(new Answer<List<User>>() {

			@Override
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				
				return Lists.newArrayList(new User(),new User(),new User());
			}
			
		});
		Mockito.when(userRepository.findAll(specification)).thenReturn(Lists.newArrayList(new User(),new User()));
		Mockito.when(userRepository.findAll(pageable)).thenReturn(page);
	}
	
	@Test
	public void getUserListTest() {
		 List<User> findAll = bootService.getUserList();
		 Assert.assertEquals(2, findAll.size());
	}
	@Test
	public void getUserByIdTest() {
		User user=bootService.getUserById("1");
		Assert.assertEquals("xiaoming", user.getName());
	}
	@Test
	public void addUserTest() {
		User user =new User();
		bootService.addUser(user);
		verify(userRepository,times(1)).save(user);
	}
	@Test
	public void deleteUserByIdTest() {
		bootService.deleteUserById("3");
		verify(userRepository,times(1)).delete(3);
	}
	@Test
	public void updateUserByIdTest() {
		User updateUserById = bootService.updateUserById("1", new User());
		Assert.assertEquals("niuniu", updateUserById.getName());
	}
	

	@Test
	public void pageQueryTest() {
		 Page<User> pageQuery = bootService.pageQuery(pageable);
		 List<User> content = pageQuery.getContent();
			Assert.assertEquals(2, content.size());

	}
	
	@Test
	public void queryByNameTest() {
		List<User> queryByName = bootService.queryByName("xiaoming");
		Assert.assertEquals(2, queryByName.size());
	}
	@Test
	public void queryByKeywordTest() {
		 List<User> queryByKeyword = bootService.queryByKeyword("xiaoming");
		Assert.assertEquals(2, queryByKeyword.size());
	}
}
