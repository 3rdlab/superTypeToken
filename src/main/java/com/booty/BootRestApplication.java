package com.booty;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BootRestApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(BootRestApplication.class, args);
	}
	
	static class GenericService<T> {
		T t;
	}
	
	@Component
	static class MyService1 extends GenericService<String>{
	}
	
	@Component
	static class MyService2 extends GenericService<Integer>{
	}
	
	@RestController
	public static class MyController{
		
		@RequestMapping("/")
		public List<User> users(){
			return Arrays.asList(new User("A"), new User("B"),new User("C"));
		}
	}
	
	
	public static class User{
		String name;
		public User(String name){
			this.name = name;
		}
		
		public User(){}

		public String getName() {
			return name;
		}
		
		public String toString(){
			return this.name;
		}
		
	}
}
