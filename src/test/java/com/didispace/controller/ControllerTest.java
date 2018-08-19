package com.didispace.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.didispace.domain.User;
import com.didispace.service.BootService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
public class ControllerTest {
	private MockMvc mvc;
@Mock
BootService bService;
@InjectMocks
BootController bController;

@Before
public void setUp() {
	MockitoAnnotations.initMocks(this);
	mvc=MockMvcBuilders.standaloneSetup(bController).build();
	Mockito.when(bService.getUserList()).thenReturn(Lists.newArrayList(new User(),new User(),new User()));
	Mockito.when(bService.getUserById("1")).thenReturn(new User(1,"vigoo"));
	Mockito.when(bService.queryByName("阿")).thenReturn(Lists.newArrayList(new User(1,"allen"),new User(2,"james")));
	Mockito.when(bService.queryByKeyword("阿")).thenReturn(Lists.newArrayList(new User(3,"ori"),new User(4,"loken")));
}
@Test
public void getUserListTest() throws Exception {
	mvc.perform(get("/api/"))
	.andExpect(status().isOk()).andDo(print());
}
@Test
public void getUserByIdTest() throws Exception {
	mvc.perform(get("/api/1"))
	.andExpect(status().isOk()).andDo(print());
}
@Test
public void addUserTest() throws Exception {
	User user=new User();
	user.setNum(97);
	user.setName("ali");
	user.setGender("F");
	user.setStaffname("爆头");
	mvc.perform(post("/api/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"num\": 97,\"name\": \"ali\",\"gender\": \"F\",\"staffname\": \"爆头\"}"))
	.andExpect(status().isOk());
}
@Test
public void deleteUserByIdTest() throws Exception {
	mvc.perform(delete("/api/3")).andExpect(status().isOk());
	verify(bService,times(1)).deleteUserById("3");
}
@Test
public void updateUserByIdTest() throws Exception {
	User user =new User ();
	user.setName("niuniu");
	mvc.perform(put("/api/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content("{\"name\":\"niuniu\",\"gender\":\"F\"}")).andExpect(status().isOk());	
}
/*@Test
public void pageQueryTest() throws Exception {
mvc.perform(get("/api/page_query")).andExpect(status().isOk()).andDo(print());
	
}*/
@Test
public void queryByNameTest() throws Exception {
	mvc.perform(get("/api/queryByName").param("name", "阿")).
	andExpect(status().isOk()).andDo(print());
}
@Test
public void queryByKeywordTest() throws Exception {
	mvc.perform(get("/api/keyword").param("keyword", "阿")).andExpect(status().isOk()).andDo(print());

}
}
