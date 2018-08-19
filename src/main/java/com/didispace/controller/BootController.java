package com.didispace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.domain.User;
import com.didispace.service.BootService;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api")
public class BootController {

	@Autowired
	private BootService bootService;
	@ApiOperation(value="获取用户列表", notes="获取所有用户列表")  
	@GetMapping("/")
	public List<User> getUserList() {
		return bootService.getUserList();
		
	}
	@ApiOperation(value="添加用户", notes="添加一个新用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@PostMapping("/")
	public void addUser(@RequestBody User user) {
		bootService.addUser(user);
	}
	@ApiOperation(value="获取相应用户", notes="根据id获取相应用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return bootService.getUserById(String.valueOf(id));
	}
	@ApiOperation(value="更新相应用户", notes="根据id更新相应用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@PutMapping("/{id}")
	public void updateUserById(@PathVariable String id,@RequestBody User user) {
		bootService.updateUserById(id,user);
	}
	@ApiOperation(value="删除相应用户", notes="根据id删除相应用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable String id) {
		bootService.deleteUserById(id);
	}
	@ApiOperation(value="模糊查询用户", notes="根据名称查询相关用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@GetMapping("/queryByName")
	public List<User> queryByName(@RequestParam String name) {
		return bootService.queryByName(name);
	}
	@ApiOperation(value="分页查询", notes="分页查询用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@GetMapping("/page_query")
	public Page<User> pageQuery(@RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "15")Integer size,Pageable pageRequest ) {	
				return bootService.pageQuery(pageRequest);
	}
	@ApiOperation(value="关键词搜索", notes="根据关键词查询用户")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@GetMapping("/keyword")
	public List<User> queryByKeyword(@RequestParam String keyword) {	
	return bootService.queryByKeyword(keyword);
	}
	
	@ApiOperation(value="查询天气", notes="天气接口")  
	@ApiResponses(value= {@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败"),@ApiResponse(code=500,message="哦豁")})
	@GetMapping("/restTemplate")
	public String  queryRestTemplate() {	
	return bootService.queryRestTemplate();
	}
}
