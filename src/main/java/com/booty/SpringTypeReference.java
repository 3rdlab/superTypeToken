package com.booty;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.booty.BootRestApplication.User;

public class SpringTypeReference {

	public static void main(String[] args) {
		/*
		ParameterizedTypeReference typeRef = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>(){};
		System.out.println(typeRef.getType());
		*/
		
		RestTemplate rt = new RestTemplate();
		//List<User> users = rt.getForObject("http://localhost:8080", List.class);

		List<User> users = rt.exchange("http://localhost:8080", 
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){}).getBody(); 
		List<User> users2 = rt.exchange("http://localhost:8080", 
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){}).getBody(); 
		
		users.forEach(System.out::println);
		
	}
}
